package com.mt.cms.service.publisher.impl;

import com.mt.cms.dao.publisher.PublisherDao;

import com.mt.common.core.exception.BusinessException;
import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.common.core.web.base.BaseEntity;
import com.mt.common.core.web.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mt.cms.entity.publisher.Publisher;
import com.mt.cms.service.publisher.PublisherService;
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
public class PublisherServiceBean extends BaseService implements PublisherService {

    private static Logger logger = LogManager.getLogger();

	@Autowired
	private PublisherDao publisherDao;

	@Resource
	private RedisTemplate<String, List<Publisher>> redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * 根据分页参数查询发布者信息集合
	 *
	 * @param pageDTO 分页条件
	 */
	@Override
	public PageResultDTO findPublishers(PageDTO pageDTO){
        pageDTO.setStartIndex((pageDTO.getCurrentPage()-1)*pageDTO.getPageSize());
		//TODO:请在此校验参数的合法性
		this.validateFindPublishers(pageDTO);
		List<Publisher> publisherDTOS = this.publisherDao.findPublishers(pageDTO);
		Long totalCount = this.publisherDao.findPublisherTotalCount(pageDTO);

		PageResultDTO pageResultDTO = new PageResultDTO();
		pageResultDTO.setTotalCount(totalCount);
		pageResultDTO.setDatas(publisherDTOS);

		return pageResultDTO;
	}

	/**
	 * 查询全部发布者信息集合
	 *
	 */
	@Override
	public List<Publisher> findAllPublishers(){
		return this.publisherDao.findAllPublishers();
	}

	/**
	 * 查询所有发布者信息集合(只提取ID 和 Name)
	 *
	 */
	@Override
	public List<Publisher> findAllPublishersWithIdName(){
		//TODO:请在此校验参数的合法性
		this.validateFindAllPublishersWithIdName();
		return this.publisherDao.findAllPublishersWithIdName();
	}

	/**
	 * 根据名称查询发布者信息集合(只提取ID 和 Name)
	 *
	 * @param publisherName 名称
	 */
	@Override
	public List<Publisher> findPublishersWithIdNameByName(String publisherName){
		//TODO:请在此校验参数的合法性
		this.validateFindPublishersWithIdNameByName(publisherName);
		//TODO:缓存取对应参数
		Set<String> keys = stringRedisTemplate.keys("searchData:Publisher_where_publisherName_" + publisherName);
		List<Publisher> publishers = new ArrayList<>();
		if (keys.isEmpty()) {
		publishers = this.publisherDao.findPublishersWithIdNameByName(publisherName);
		redisTemplate.opsForValue().set("searchData:Publisher_where_publisherName_" + publisherName, publishers, 30, TimeUnit.DAYS);
		} else {
		publishers = redisTemplate.opsForValue().get("searchData:Publisher_where_publisherName_" + publisherName);
		}
		return publishers;
	}

	/**
	 * 根据ID查询指定的发布者信息(只提取ID 和 Name)
	 *
	 * @param publisherId Id
	 */
	@Override
	public Publisher findPublishersWithIdNameById(Long publisherId){
		//TODO:请在此校验参数的合法性
		this.validateFindPublishersWithIdNameById(publisherId);
		return this.publisherDao.findPublishersWithIdNameById(publisherId);
	}

	/**
	 * 根据ID查询指定的发布者信息
	 *
	 * @param publisherId Id
	 */
	@Override
	public Publisher findPublisher(Long publisherId){
		//TODO:请在此校验参数的合法性
		this.validateFindPublisher(publisherId);
		return this.publisherDao.findPublisher(publisherId);
	}

	/**
	 * 根据ID查询指定的发布者信息(包含外键)
	 *
	 * @param publisherId Id
	 */
	@Override
	public Publisher findPublisherWithForeignName(Long publisherId){
		//TODO:请在此校验参数的合法性
		this.validateFindPublisherWithForeignName(publisherId);
		return this.publisherDao.findPublisherWithForeignName(publisherId);
	}

	/**
	 * 新增发布者信息
	 *
	 * @param publisher 实体对象
	 */
	@Override
	public Publisher savePublisher(Publisher publisher){
		//TODO:请在此校验参数的合法性
		this.validateSavePublisher(publisher);
		//TODO:填充公共参数
		this.setSavePulicColumns(publisher);
		Long rows = this.publisherDao.savePublisher(publisher);
		if(rows != 1)
		{
			String error = "新增保存发布者信息出错，数据库应该返回1,但返回了 "+rows;
			throw new BusinessException(error);
		}
		return publisher;
	}

	/**
	 * 更新发布者信息
	 *
	 * @param publisher 实体对象
	 */
	@Override
	public Publisher updatePublisher(Publisher publisher){
		//TODO:请在此校验参数的合法性
		this.validateUpdatePublisher(publisher);
		Long rows = this.publisherDao.updatePublisher(publisher);
		if(rows != 1)
		{
			String error = "修改保存发布者信息出错，数据库应该返回1,但返回了 "+rows+",数据可能被删除";
			throw new BusinessException(error);
		}
		return publisher;
	}

	/**
	 * 根据ID删除发布者信息
	 *
	 * @param publisherId ID
	 */
	@Override
	public void deletePublisher(Long publisherId){
		//TODO:请在此校验参数的合法性
		this.validateDeletePublisher(publisherId);

		Map<Class<? extends BaseEntity>,EntityUsage> entityUsageMap = this.checkForeignEntity(Publisher.class, publisherId);
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

		Long rows = this.publisherDao.deletePublisher(publisherId);
		if(rows != 1){
			String error = "删除发布者信息出错，数据可能已经被删除";
			throw new BusinessException(error);
		}
	}

	//TODO:---------------验证-------------------

	private void validateFindPublishers(PageDTO pageDTO) {
	//TODO:请使用下面方法添加数据过滤条件
	//		pageDTO.addFilter("creatorId",this.getLoginUserId());
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateFindPublishersWithIdNameByName(String publisherName) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}


	private void validateFindAllPublishersWithIdName() {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateFindPublishersWithIdNameById(Long publisherId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateFindPublisher(Long publisherId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateFindPublisherWithForeignName(Long publisherId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateSavePublisher(Publisher publisher) {
	//不为空判断
	if (publisher.getEid() != null || publisher.getCreatorId() != null || publisher.getCreateDatetime() != null) {
	throw new BusinessException("非法请求");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateUpdatePublisher(Publisher publisher) {
	//不为空判断
	if (publisher.getEid() == null) {
	throw new BusinessException("唯一标识不能为空");
	}
	//是否存在判断
	if (this.publisherDao.findPublisherTotalCount(PageDTO.create(Publisher.FIELD_ID, publisher.getEid())) == 0) {
	throw new BusinessException("修改的发布者信息 " + publisher.getName() + " 不存在，修改失败，请重试或联系管理员");
	}
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	private void validateDeletePublisher(Long publisherId) {
	//TODO:请完善数据校验规则和数据权限判断，如果有问题请抛出异常，参看下面validateUpdatePublisher()写法
	}

	@Override
	public boolean canDownloadAttachment(String formName, Long id) {
	return true;
	}
}
