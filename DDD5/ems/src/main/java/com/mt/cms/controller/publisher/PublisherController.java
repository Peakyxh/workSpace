

package com.mt.cms.controller.publisher;

import org.springframework.security.access.prepost.PreAuthorize;
import com.mt.common.core.annotation.ApiPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.mt.cms.dto.publisher.PublisherEditDto;
import com.mt.cms.entity.publisher.Publisher;
import com.mt.cms.service.publisher.PublisherService;
										

@Api(tags = "发布者信息")
@RestController
@RequestMapping("/api/cms/publisher/Publisher")
@CrossOrigin(allowCredentials = "true")
public class PublisherController {
private static Logger logger = LoggerFactory.getLogger(PublisherController.class);



																																	@Autowired private PublisherService publisherService;
	
	/**
	* 根据分页参数查询发布者信息集合
	*
	* @param pageDTO 分页条件
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:view')")
	@ApiOperation("分页查询发布者信息")
	@ApiPageParam
	@PostMapping("/findPublishers")
	public PageResultDTO findPublishers(@RequestBody PageDTO pageDTO){
		return this.publisherService.findPublishers(pageDTO);
	}

	/**
	* 根据ID查询指定的发布者信息
	*
	* @param publisherId Id
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:view')")
	@ApiOperation("根据id查询发布者信息")
	@ApiPageParam
	@PostMapping("/findPublisher")
	public Publisher findPublisher(@RequestParam Long publisherId){
		return this.publisherService.findPublisher(publisherId);
	}

	/**
	* 根据ID查询指定的发布者信息(包含外键名称)
	*
	* @param publisherId Id
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:view')")
	@ApiOperation("根据ID查询指定的发布者信息(包含外键名称)")
	@PostMapping("/findPublisherForView")
	public Publisher findPublisherForView(@RequestParam Long publisherId){
		return this.publisherService.findPublisherWithForeignName(publisherId);
	}

	/**
	* 根据ID查询指定的发布者信息(包含发布者信息和外键名称)
	*
	* @param publisherId Id
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:view')")
	@ApiOperation("根据ID查询指定的发布者信息(包含发布者信息和外键名称)")
	@PostMapping("/findPublisherForEdit")
	public PublisherEditDto findPublisherForEdit(@RequestParam Long publisherId){
				PublisherEditDto publisherEditDto = new PublisherEditDto();
		publisherEditDto.setPublisher(this.publisherService.findPublisherWithForeignName(publisherId));

		this.preparePublisherEditDto(publisherEditDto);

		return publisherEditDto;
	}

	/**
	* 根据ID查询指定的发布者信息(只提取ID 和 Name)
	*
	* @param publisherId Id
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:view')")
	@ApiOperation("根据ID查询指定的发布者信息(只提取ID 和 Name)")
	@PostMapping("/findPublishersWithIdNameById")
	public Publisher findPublishersWithIdNameById(@RequestParam Long publisherId){
	return this.publisherService.findPublishersWithIdNameById(publisherId);
	}

	/**
	* 根据名称查询发布者信息集合(只提取ID 和 Name)
	*
	* @param publisherName 名称
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:view')")
	@ApiOperation("根据名称查询发布者信息集合(只提取ID 和 Name)")
	@PostMapping("/findPublishersWithIdNameByName")
	public List<Publisher> findPublishersWithIdNameByName(@RequestParam String publisherName){
	return this.publisherService.findPublishersWithIdNameByName(publisherName);
	}


	/**
	* 创建新的发布者信息
	*
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:add')")
	@ApiOperation("创建新的发布者信息")
	@PostMapping("/createPublisher")
	public PublisherEditDto createPublisher(){
				PublisherEditDto publisherEditDto = new PublisherEditDto();
		publisherEditDto.setPublisher(new Publisher());

		this.preparePublisherEditDto(publisherEditDto);
		return publisherEditDto;
	}

	private void preparePublisherEditDto(PublisherEditDto publisherEditDto){
																																}

	/**
	* 新增保存发布者信息
	*
	* @param publisher 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:add')")
	@ApiOperation("新增保存发布者信息")
	@PostMapping("/savePublisher")
	public Publisher savePublisher(@RequestBody Publisher publisher){
		return this.publisherService.savePublisher(publisher);
	}

	/**
	* 修改保存发布者信息
	*
	* @param publisher 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:update')")
	@ApiOperation("修改保存发布者信息")
	@PostMapping("/updatePublisher")
	public Publisher updatePublisher(@RequestBody Publisher publisher){
		return this.publisherService.updatePublisher(publisher);
	}

	/**
	* 根据ID删除发布者信息
	*
	* @param publisherId ID
	*/
	@PreAuthorize("hasAuthority('cms:publisher:Publisher:remove')")
	@ApiOperation("根据ID删除发布者信息")
	@PostMapping("/deletePublisher")
	public void deletePublisher(@RequestParam Long publisherId){
		this.publisherService.deletePublisher(publisherId);
	}



}

