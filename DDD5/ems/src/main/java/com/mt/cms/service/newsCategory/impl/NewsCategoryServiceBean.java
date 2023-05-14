package com.mt.cms.service.newsCategory.impl;

import com.mt.cms.dao.newsCategory.NewsCategoryDao;

import com.mt.common.core.exception.BusinessException;
import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.common.core.web.base.BaseEntity;
import com.mt.common.core.web.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mt.cms.entity.newsCategory.NewsCategory;
import com.mt.cms.service.newsCategory.NewsCategoryService;
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
public class NewsCategoryServiceBean extends BaseService implements NewsCategoryService {

    private static Logger logger = LogManager.getLogger();

	@Autowired
	private NewsCategoryDao newsCategoryDao;

	@Resource
	private RedisTemplate<String, List<NewsCategory>> redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * 根据分页参数查询新闻类别集合
	 *
	 * @param pageDTO 分页条件
	 */
	@Override
	public PageResultDTO findNewsCategorys(PageDTO pageDTO){
        pageDTO.setStartIndex((pageDTO.getCurrentPage()-1)*pageDTO.getPageSize());
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCategorys(pageDTO);
		List<NewsCategory> newsCategoryDTOS = this.newsCategoryDao.findNewsCategorys(pageDTO);
		Long totalCount = this.newsCategoryDao.findNewsCategoryTotalCount(pageDTO);

		PageResultDTO pageResultDTO = new PageResultDTO();
		pageResultDTO.setTotalCount(totalCount);
		pageResultDTO.setDatas(newsCategoryDTOS);

		return pageResultDTO;
	}

	/**
	 * 查询全部新闻类别集合
	 *
	 */
	@Override
	public List<NewsCategory> findAllNewsCategorys(){
		return this.newsCategoryDao.findAllNewsCategorys();
	}

	/**
	 * 查询所有新闻类别集合(只提取ID 和 Name)
	 *
	 */
	@Override
	public List<NewsCategory> findAllNewsCategorysWithIdName(){
		//TODO:请在此校验参数的合法性
		this.validateFindAllNewsCategorysWithIdName();
		return this.newsCategoryDao.findAllNewsCategorysWithIdName();
	}

	/**
	 * 根据名称查询新闻类别集合(只提取ID 和 Name)
	 *
	 * @param newsCategoryName 名称
	 */
	@Override
	public List<NewsCategory> findNewsCategorysWithIdNameByName(String newsCategoryName){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCategorysWithIdNameByName(newsCategoryName);
		//TODO:缓存取对应参数
		Set<String> keys = stringRedisTemplate.keys("searchData:NewsCategory_where_newsCategoryName_" + newsCategoryName);
		List<NewsCategory> newsCategorys = new ArrayList<>();
		if (keys.isEmpty()) {
		newsCategorys = this.newsCategoryDao.findNewsCategorysWithIdNameByName(newsCategoryName);
		redisTemplate.opsForValue().set("searchData:NewsCategory_where_newsCategoryName_" + newsCategoryName, newsCategorys, 30, TimeUnit.DAYS);
		} else {
		newsCategorys = redisTemplate.opsForValue().get("searchData:NewsCategory_where_newsCategoryName_" + newsCategoryName);
		}
		return newsCategorys;
	}

	/**
	 * 根据ID查询指定的新闻类别(只提取ID 和 Name)
	 *
	 * @param newsCategoryId Id
	 */
	@Override
	public NewsCategory findNewsCategorysWithIdNameById(Long newsCategoryId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCategorysWithIdNameById(newsCategoryId);
		return this.newsCategoryDao.findNewsCategorysWithIdNameById(newsCategoryId);
	}

	/**
	 * 根据ID查询指定的新闻类别
	 *
	 * @param newsCategoryId Id
	 */
	@Override
	public NewsCategory findNewsCategory(Long newsCategoryId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCategory(newsCategoryId);
		return this.newsCategoryDao.findNewsCategory(newsCategoryId);
	}

	/**
	 * 根据ID查询指定的新闻类别(包含外键)
	 *
	 * @param newsCategoryId Id
	 */
	@Override
	public NewsCategory findNewsCategoryWithForeignName(Long newsCategoryId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCategoryWithForeignName(newsCategoryId);
		return this.newsCategoryDao.findNewsCategoryWithForeignName(newsCategoryId);
	}

	/**
	 * 新增新闻类别
	 *
	 * @param newsCategory 实体对象
	 */
	@Override
	public NewsCategory saveNewsCategory(NewsCategory newsCategory){
		//TODO:请在此校验参数的合法性
		this.validateSaveNewsCategory(newsCategory);
		//TODO:填充公共参数
		this.setSavePulicColumns(newsCategory);
		Long rows = this.newsCategoryDao.saveNewsCategory(newsCategory);
		if(rows != 1)
		{
			String error = "新增保存新闻类别出错，数据库应该返回1,但返回了 "+rows;
			throw new BusinessException(error);
		}
		return newsCategory;
	}

	/**
	 * 更新新闻类别
	 *
	 * @param newsCategory 实体对象
	 */
	@Override
	public NewsCategory updateNewsCategory(NewsCategory newsCategory){
		//TODO:请在此校验参数的合法性
		this.validateUpdateNewsCategory(newsCategory);
		Long rows = this.newsCategoryDao.updateNewsCategory(newsCategory);
		if(rows != 1)
		{
			String error = "修改保存新闻类别出错，数据库应该返回1,但返回了 "+rows+",数据可能被删除";
			throw new BusinessException(error);
		}
		return newsCategory;
	}

	/**
	 * 根据ID删除新闻类别
	 *
	 * @param newsCategoryId ID
	 */
	@Override
	public void deleteNewsCategory(Long newsCategoryId){
		//TODO:请在此校验参数的合法性
		this.validateDeleteNewsCategory(newsCategoryId);

		Map<Class<? extends BaseEntity>,EntityUsage> entityUsageMap = this.checkForeignEntity(NewsCategory.class, newsCategoryId);
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

		Long rows = this.newsCategoryDao.deleteNewsCategory(newsCategoryId);
		if(rows != 1){
			String error = "删除新闻类别出错，数据可能已经被删除";
			throw new BusinessException(error);
		}
	}

	//TODO:---------------验证-------------------

	private void validateFindNewsCategorys(PageDTO pageDTO) {
	//TODO:请使用下面方法添加数据过滤条件
	//		pageDTO.addFilter("creatorId",this.getLoginUserId());
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateFindNewsCategorysWithIdNameByName(String newsCategoryName) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}


	private void validateFindAllNewsCategorysWithIdName() {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateFindNewsCategorysWithIdNameById(Long newsCategoryId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateFindNewsCategory(Long newsCategoryId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateFindNewsCategoryWithForeignName(Long newsCategoryId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateSaveNewsCategory(NewsCategory newsCategory) {
	//不为空判断
	if (newsCategory.getEid() != null || newsCategory.getCreatorId() != null || newsCategory.getCreateDatetime() != null) {
	throw new BusinessException("非法请求");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateUpdateNewsCategory(NewsCategory newsCategory) {
	//不为空判断
	if (newsCategory.getEid() == null) {
	throw new BusinessException("唯一标识不能为空");
	}
	//是否存在判断
	if (this.newsCategoryDao.findNewsCategoryTotalCount(PageDTO.create(NewsCategory.FIELD_ID, newsCategory.getEid())) == 0) {
	throw new BusinessException("修改的新闻类别 " + newsCategory.getName() + " 不存在，修改失败，请重试或联系管理员");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	private void validateDeleteNewsCategory(Long newsCategoryId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsCategory()写法
	}

	@Override
	public boolean canDownloadAttachment(String formName, Long id) {
	return true;
	}
}
