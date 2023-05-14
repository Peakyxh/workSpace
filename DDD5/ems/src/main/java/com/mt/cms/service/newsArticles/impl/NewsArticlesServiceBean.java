package com.mt.cms.service.newsArticles.impl;

import com.mt.cms.dao.newsArticles.NewsArticlesDao;

import com.mt.common.core.exception.BusinessException;
import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.common.core.web.base.BaseEntity;
import com.mt.common.core.web.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mt.cms.entity.newsArticles.NewsArticles;
import com.mt.cms.service.newsArticles.NewsArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class NewsArticlesServiceBean extends BaseService implements NewsArticlesService {

    private static Logger logger = LogManager.getLogger();

	@Autowired
	private NewsArticlesDao newsArticlesDao;

	@Resource
	private RedisTemplate<String, List<NewsArticles>> redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * 根据分页参数查询新闻文章集合
	 *
	 * @param pageDTO 分页条件
	 */
	@Override
	public PageResultDTO findNewsArticless(PageDTO pageDTO){
        pageDTO.setStartIndex((pageDTO.getCurrentPage()-1)*pageDTO.getPageSize());
		//TODO:请在此校验参数的合法性
		this.validateFindNewsArticless(pageDTO);
		List<NewsArticles> newsArticlesDTOS = this.newsArticlesDao.findNewsArticless(pageDTO);
		Long totalCount = this.newsArticlesDao.findNewsArticlesTotalCount(pageDTO);

		PageResultDTO pageResultDTO = new PageResultDTO();
		pageResultDTO.setTotalCount(totalCount);
		pageResultDTO.setDatas(newsArticlesDTOS);

		return pageResultDTO;
	}

	/**
	 * 查询全部新闻文章集合
	 *
	 */
	@Override
	public List<NewsArticles> findAllNewsArticless(){
		return this.newsArticlesDao.findAllNewsArticless();
	}

	/**
	 * 查询所有新闻文章集合(只提取ID 和 Name)
	 *
	 */
	@Override
	public List<NewsArticles> findAllNewsArticlessWithIdName(){
		//TODO:请在此校验参数的合法性
		this.validateFindAllNewsArticlessWithIdName();
		return this.newsArticlesDao.findAllNewsArticlessWithIdName();
	}

	/**
	 * 根据名称查询新闻文章集合(只提取ID 和 Name)
	 *
	 * @param newsArticlesName 名称
	 */
	@Override
	public List<NewsArticles> findNewsArticlessWithIdNameByName(String newsArticlesName){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsArticlessWithIdNameByName(newsArticlesName);
		//TODO:缓存取对应参数
		Set<String> keys = stringRedisTemplate.keys("searchData:NewsArticles_where_newsArticlesName_" + newsArticlesName);
		List<NewsArticles> newsArticless = new ArrayList<>();
		if (keys.isEmpty()) {
		newsArticless = this.newsArticlesDao.findNewsArticlessWithIdNameByName(newsArticlesName);
		redisTemplate.opsForValue().set("searchData:NewsArticles_where_newsArticlesName_" + newsArticlesName, newsArticless, 30, TimeUnit.DAYS);
		} else {
		newsArticless = redisTemplate.opsForValue().get("searchData:NewsArticles_where_newsArticlesName_" + newsArticlesName);
		}
		return newsArticless;
	}

	/**
	 * 根据ID查询指定的新闻文章(只提取ID 和 Name)
	 *
	 * @param newsArticlesId Id
	 */
	@Override
	public NewsArticles findNewsArticlessWithIdNameById(Long newsArticlesId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsArticlessWithIdNameById(newsArticlesId);
		return this.newsArticlesDao.findNewsArticlessWithIdNameById(newsArticlesId);
	}

	/**
	 * 根据ID查询指定的新闻文章
	 *
	 * @param newsArticlesId Id
	 */
	@Override
	public NewsArticles findNewsArticles(Long newsArticlesId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsArticles(newsArticlesId);
		return this.newsArticlesDao.findNewsArticles(newsArticlesId);
	}

	/**
	 * 根据ID查询指定的新闻文章(包含外键)
	 *
	 * @param newsArticlesId Id
	 */
	@Override
	public NewsArticles findNewsArticlesWithForeignName(Long newsArticlesId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsArticlesWithForeignName(newsArticlesId);
		return this.newsArticlesDao.findNewsArticlesWithForeignName(newsArticlesId);
	}

	/**
	 * 新增新闻文章
	 *
	 * @param newsArticles 实体对象
	 */
	@Override
	public NewsArticles saveNewsArticles(NewsArticles newsArticles){
		//TODO:请在此校验参数的合法性
		this.validateSaveNewsArticles(newsArticles);
		//TODO:填充公共参数
		this.setSavePulicColumns(newsArticles);
		Long rows = this.newsArticlesDao.saveNewsArticles(newsArticles);
		if(rows != 1)
		{
			String error = "新增保存新闻文章出错，数据库应该返回1,但返回了 "+rows;
			throw new BusinessException(error);
		}
		return newsArticles;
	}

	/**
	 * 更新新闻文章
	 *
	 * @param newsArticles 实体对象
	 */
	@Override
	public NewsArticles updateNewsArticles(NewsArticles newsArticles){
		//TODO:请在此校验参数的合法性
		this.validateUpdateNewsArticles(newsArticles);
		Long rows = this.newsArticlesDao.updateNewsArticles(newsArticles);
		if(rows != 1)
		{
			String error = "修改保存新闻文章出错，数据库应该返回1,但返回了 "+rows+",数据可能被删除";
			throw new BusinessException(error);
		}
		return newsArticles;
	}

	/**
	 * 根据ID删除新闻文章
	 *
	 * @param newsArticlesId ID
	 */
	@Override
	public void deleteNewsArticles(Long newsArticlesId){
		//TODO:请在此校验参数的合法性
		this.validateDeleteNewsArticles(newsArticlesId);

		Map<Class<? extends BaseEntity>,EntityUsage> entityUsageMap = this.checkForeignEntity(NewsArticles.class, newsArticlesId);
		if(entityUsageMap != null && entityUsageMap.size() >0){
			StringBuilder errors = new StringBuilder();
			errors.append("计划删除的数据正在被以下数引用\n");
			for(EntityUsage entityUsage : entityUsageMap.values()){
				errors.append("\t").append(entityUsage.getEntityLabel()).append("\n");
				for(Map.Entry<Long,String> entry : entityUsage.getUsageIdNames().entrySet() ){
					errors.append("\t\t").append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
				}
			}
			errors.append("，不能删除，请检查处理后再删除");
			throw  new BusinessException(errors.toString());
		}

		Long rows = this.newsArticlesDao.deleteNewsArticles(newsArticlesId);
		if(rows != 1){
			String error = "删除新闻文章出错，数据可能已经被删除";
			throw new BusinessException(error);
		}
	}

	//TODO:---------------验证-------------------

	private void validateFindNewsArticless(PageDTO pageDTO) {
	//TODO:请使用下面方法添加数据过滤条件
	//		pageDTO.addFilter("creatorId",this.getLoginUserId());
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateFindNewsArticlessWithIdNameByName(String newsArticlesName) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}


	private void validateFindAllNewsArticlessWithIdName() {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateFindNewsArticlessWithIdNameById(Long newsArticlesId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateFindNewsArticles(Long newsArticlesId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateFindNewsArticlesWithForeignName(Long newsArticlesId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateSaveNewsArticles(NewsArticles newsArticles) {
	//不为空判断
	if (newsArticles.getEid() != null || newsArticles.getCreatorId() != null || newsArticles.getCreateDatetime() != null) {
	throw new BusinessException("非法请求");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateUpdateNewsArticles(NewsArticles newsArticles) {
	//不为空判断
	if (newsArticles.getEid() == null) {
	throw new BusinessException("唯一标识不能为空");
	}
	//是否存在判断
	if (this.newsArticlesDao.findNewsArticlesTotalCount(PageDTO.create(NewsArticles.FIELD_ID, newsArticles.getEid())) == 0) {
	throw new BusinessException("修改的新闻文章 " + newsArticles.getName() + " 不存在，修改失败，请重试或联系管理员");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	private void validateDeleteNewsArticles(Long newsArticlesId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsArticles()写法
	}

	@Override
	public boolean canDownloadAttachment(String formName, Long id) {
	return true;
	}
}
