package com.mt.cms.service.newsComment.impl;

import com.mt.cms.dao.newsComment.NewsCommentDao;

import com.mt.common.core.exception.BusinessException;
import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.common.core.web.base.BaseEntity;
import com.mt.common.core.web.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mt.cms.entity.newsComment.NewsComment;
import com.mt.cms.service.newsComment.NewsCommentService;
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
public class NewsCommentServiceBean extends BaseService implements NewsCommentService {

    private static Logger logger = LogManager.getLogger();

	@Autowired
	private NewsCommentDao newsCommentDao;

	@Resource
	private RedisTemplate<String, List<NewsComment>> redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * 根据分页参数查询新闻评论集合
	 *
	 * @param pageDTO 分页条件
	 */
	@Override
	public PageResultDTO findNewsComments(PageDTO pageDTO){
        pageDTO.setStartIndex((pageDTO.getCurrentPage()-1)*pageDTO.getPageSize());
		//TODO:请在此校验参数的合法性
		this.validateFindNewsComments(pageDTO);
		List<NewsComment> newsCommentDTOS = this.newsCommentDao.findNewsComments(pageDTO);
		Long totalCount = this.newsCommentDao.findNewsCommentTotalCount(pageDTO);

		PageResultDTO pageResultDTO = new PageResultDTO();
		pageResultDTO.setTotalCount(totalCount);
		pageResultDTO.setDatas(newsCommentDTOS);

		return pageResultDTO;
	}

	/**
	 * 查询全部新闻评论集合
	 *
	 */
	@Override
	public List<NewsComment> findAllNewsComments(){
		return this.newsCommentDao.findAllNewsComments();
	}

	/**
	 * 查询所有新闻评论集合(只提取ID 和 Name)
	 *
	 */
	@Override
	public List<NewsComment> findAllNewsCommentsWithIdName(){
		//TODO:请在此校验参数的合法性
		this.validateFindAllNewsCommentsWithIdName();
		return this.newsCommentDao.findAllNewsCommentsWithIdName();
	}

	/**
	 * 根据名称查询新闻评论集合(只提取ID 和 Name)
	 *
	 * @param newsCommentName 名称
	 */
	@Override
	public List<NewsComment> findNewsCommentsWithIdNameByName(String newsCommentName){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCommentsWithIdNameByName(newsCommentName);
		//TODO:缓存取对应参数
		Set<String> keys = stringRedisTemplate.keys("searchData:NewsComment_where_newsCommentName_" + newsCommentName);
		List<NewsComment> newsComments = new ArrayList<>();
		if (keys.isEmpty()) {
		newsComments = this.newsCommentDao.findNewsCommentsWithIdNameByName(newsCommentName);
		redisTemplate.opsForValue().set("searchData:NewsComment_where_newsCommentName_" + newsCommentName, newsComments, 30, TimeUnit.DAYS);
		} else {
		newsComments = redisTemplate.opsForValue().get("searchData:NewsComment_where_newsCommentName_" + newsCommentName);
		}
		return newsComments;
	}

	/**
	 * 根据ID查询指定的新闻评论(只提取ID 和 Name)
	 *
	 * @param newsCommentId Id
	 */
	@Override
	public NewsComment findNewsCommentsWithIdNameById(Long newsCommentId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCommentsWithIdNameById(newsCommentId);
		return this.newsCommentDao.findNewsCommentsWithIdNameById(newsCommentId);
	}

	/**
	 * 根据ID查询指定的新闻评论
	 *
	 * @param newsCommentId Id
	 */
	@Override
	public NewsComment findNewsComment(Long newsCommentId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsComment(newsCommentId);
		return this.newsCommentDao.findNewsComment(newsCommentId);
	}

	/**
	 * 根据ID查询指定的新闻评论(包含外键)
	 *
	 * @param newsCommentId Id
	 */
	@Override
	public NewsComment findNewsCommentWithForeignName(Long newsCommentId){
		//TODO:请在此校验参数的合法性
		this.validateFindNewsCommentWithForeignName(newsCommentId);
		return this.newsCommentDao.findNewsCommentWithForeignName(newsCommentId);
	}

	/**
	 * 新增新闻评论
	 *
	 * @param newsComment 实体对象
	 */
	@Override
	public NewsComment saveNewsComment(NewsComment newsComment){
		//TODO:请在此校验参数的合法性
		this.validateSaveNewsComment(newsComment);
		//TODO:填充公共参数
		this.setSavePulicColumns(newsComment);
		Long rows = this.newsCommentDao.saveNewsComment(newsComment);
		if(rows != 1)
		{
			String error = "新增保存新闻评论出错，数据库应该返回1,但返回了 "+rows;
			throw new BusinessException(error);
		}
		return newsComment;
	}

	/**
	 * 更新新闻评论
	 *
	 * @param newsComment 实体对象
	 */
	@Override
	public NewsComment updateNewsComment(NewsComment newsComment){
		//TODO:请在此校验参数的合法性
		this.validateUpdateNewsComment(newsComment);
		Long rows = this.newsCommentDao.updateNewsComment(newsComment);
		if(rows != 1)
		{
			String error = "修改保存新闻评论出错，数据库应该返回1,但返回了 "+rows+",数据可能被删除";
			throw new BusinessException(error);
		}
		return newsComment;
	}

	/**
	 * 根据ID删除新闻评论
	 *
	 * @param newsCommentId ID
	 */
	@Override
	public void deleteNewsComment(Long newsCommentId){
		//TODO:请在此校验参数的合法性
		this.validateDeleteNewsComment(newsCommentId);

		Map<Class<? extends BaseEntity>,EntityUsage> entityUsageMap = this.checkForeignEntity(NewsComment.class, newsCommentId);
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

		Long rows = this.newsCommentDao.deleteNewsComment(newsCommentId);
		if(rows != 1){
			String error = "删除新闻评论出错，数据可能已经被删除";
			throw new BusinessException(error);
		}
	}

	//TODO:---------------验证-------------------

	private void validateFindNewsComments(PageDTO pageDTO) {
	//TODO:请使用下面方法添加数据过滤条件
	//		pageDTO.addFilter("creatorId",this.getLoginUserId());
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateFindNewsCommentsWithIdNameByName(String newsCommentName) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}


	private void validateFindAllNewsCommentsWithIdName() {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateFindNewsCommentsWithIdNameById(Long newsCommentId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateFindNewsComment(Long newsCommentId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateFindNewsCommentWithForeignName(Long newsCommentId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateSaveNewsComment(NewsComment newsComment) {
	//不为空判断
	if (newsComment.getEid() != null || newsComment.getCreatorId() != null || newsComment.getCreateDatetime() != null) {
	throw new BusinessException("非法请求");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateUpdateNewsComment(NewsComment newsComment) {
	//不为空判断
	if (newsComment.getEid() == null) {
	throw new BusinessException("唯一标识不能为空");
	}
	//是否存在判断
	if (this.newsCommentDao.findNewsCommentTotalCount(PageDTO.create(NewsComment.FIELD_ID, newsComment.getEid())) == 0) {
	throw new BusinessException("修改的新闻评论 " + newsComment.getName() + " 不存在，修改失败，请重试或联系管理员");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	private void validateDeleteNewsComment(Long newsCommentId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdateNewsComment()写法
	}

	@Override
	public boolean canDownloadAttachment(String formName, Long id) {
	return true;
	}
}
