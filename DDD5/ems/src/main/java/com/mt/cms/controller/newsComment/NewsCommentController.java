

package com.mt.cms.controller.newsComment;

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



import com.mt.cms.dto.newsComment.NewsCommentEditDto;
import com.mt.cms.entity.newsComment.NewsComment;
import com.mt.cms.service.newsComment.NewsCommentService;
						import com.mt.common.system.service.UserService;
							

@Api(tags = "新闻评论")
@RestController
@RequestMapping("/api/cms/newsComment/NewsComment")
@CrossOrigin(allowCredentials = "true")
public class NewsCommentController {
private static Logger logger = LoggerFactory.getLogger(NewsCommentController.class);



																					
																							@Autowired private NewsCommentService newsCommentService;
		@Autowired private UserService userService;
	
	/**
	* 根据分页参数查询新闻评论集合
	*
	* @param pageDTO 分页条件
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:view')")
	@ApiOperation("分页查询新闻评论")
	@ApiPageParam
	@PostMapping("/findNewsComments")
	public PageResultDTO findNewsComments(@RequestBody PageDTO pageDTO){
		return this.newsCommentService.findNewsComments(pageDTO);
	}

	/**
	* 根据ID查询指定的新闻评论
	*
	* @param newsCommentId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:view')")
	@ApiOperation("根据id查询新闻评论")
	@ApiPageParam
	@PostMapping("/findNewsComment")
	public NewsComment findNewsComment(@RequestParam Long newsCommentId){
		return this.newsCommentService.findNewsComment(newsCommentId);
	}

	/**
	* 根据ID查询指定的新闻评论(包含外键名称)
	*
	* @param newsCommentId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:view')")
	@ApiOperation("根据ID查询指定的新闻评论(包含外键名称)")
	@PostMapping("/findNewsCommentForView")
	public NewsComment findNewsCommentForView(@RequestParam Long newsCommentId){
		return this.newsCommentService.findNewsCommentWithForeignName(newsCommentId);
	}

	/**
	* 根据ID查询指定的新闻评论(包含新闻评论和外键名称)
	*
	* @param newsCommentId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:view')")
	@ApiOperation("根据ID查询指定的新闻评论(包含新闻评论和外键名称)")
	@PostMapping("/findNewsCommentForEdit")
	public NewsCommentEditDto findNewsCommentForEdit(@RequestParam Long newsCommentId){
				NewsCommentEditDto newsCommentEditDto = new NewsCommentEditDto();
		newsCommentEditDto.setNewsComment(this.newsCommentService.findNewsCommentWithForeignName(newsCommentId));

		this.prepareNewsCommentEditDto(newsCommentEditDto);

		return newsCommentEditDto;
	}

	/**
	* 根据ID查询指定的新闻评论(只提取ID 和 Name)
	*
	* @param newsCommentId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:view')")
	@ApiOperation("根据ID查询指定的新闻评论(只提取ID 和 Name)")
	@PostMapping("/findNewsCommentsWithIdNameById")
	public NewsComment findNewsCommentsWithIdNameById(@RequestParam Long newsCommentId){
	return this.newsCommentService.findNewsCommentsWithIdNameById(newsCommentId);
	}

	/**
	* 根据名称查询新闻评论集合(只提取ID 和 Name)
	*
	* @param newsCommentName 名称
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:view')")
	@ApiOperation("根据名称查询新闻评论集合(只提取ID 和 Name)")
	@PostMapping("/findNewsCommentsWithIdNameByName")
	public List<NewsComment> findNewsCommentsWithIdNameByName(@RequestParam String newsCommentName){
	return this.newsCommentService.findNewsCommentsWithIdNameByName(newsCommentName);
	}


	/**
	* 创建新的新闻评论
	*
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:add')")
	@ApiOperation("创建新的新闻评论")
	@PostMapping("/createNewsComment")
	public NewsCommentEditDto createNewsComment(){
				NewsCommentEditDto newsCommentEditDto = new NewsCommentEditDto();
		newsCommentEditDto.setNewsComment(new NewsComment());

		this.prepareNewsCommentEditDto(newsCommentEditDto);
		return newsCommentEditDto;
	}

	private void prepareNewsCommentEditDto(NewsCommentEditDto newsCommentEditDto){
																		        //TODO: 以下代码可以注释掉，此行代码即时加载所有外键对象，以便选择。如果不在此加载，可以在界面上实现延迟加载。如果外键对象超过 500 行，建议采用延迟加载
		newsCommentEditDto.setCreateIdUsers(this.userService.findAllUsersWithIdName());
																						}

	/**
	* 新增保存新闻评论
	*
	* @param newsComment 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:add')")
	@ApiOperation("新增保存新闻评论")
	@PostMapping("/saveNewsComment")
	public NewsComment saveNewsComment(@RequestBody NewsComment newsComment){
		return this.newsCommentService.saveNewsComment(newsComment);
	}

	/**
	* 修改保存新闻评论
	*
	* @param newsComment 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:update')")
	@ApiOperation("修改保存新闻评论")
	@PostMapping("/updateNewsComment")
	public NewsComment updateNewsComment(@RequestBody NewsComment newsComment){
		return this.newsCommentService.updateNewsComment(newsComment);
	}

	/**
	* 根据ID删除新闻评论
	*
	* @param newsCommentId ID
	*/
	@PreAuthorize("hasAuthority('cms:newsComment:NewsComment:remove')")
	@ApiOperation("根据ID删除新闻评论")
	@PostMapping("/deleteNewsComment")
	public void deleteNewsComment(@RequestParam Long newsCommentId){
		this.newsCommentService.deleteNewsComment(newsCommentId);
	}



}

