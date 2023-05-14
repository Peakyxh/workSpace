

package com.mt.cms.controller.newsArticles;

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



import com.mt.cms.dto.newsArticles.NewsArticlesEditDto;
import com.mt.cms.entity.newsArticles.NewsArticles;
import com.mt.cms.service.newsArticles.NewsArticlesService;
																	import com.mt.cms.service.publisher.PublisherService;
				

@Api(tags = "新闻文章")
@RestController
@RequestMapping("/api/cms/newsArticles/NewsArticles")
@CrossOrigin(allowCredentials = "true")
public class NewsArticlesController {
private static Logger logger = LoggerFactory.getLogger(NewsArticlesController.class);



																																																						
														@Autowired private NewsArticlesService newsArticlesService;
		@Autowired private PublisherService publisherService;
	
	/**
	* 根据分页参数查询新闻文章集合
	*
	* @param pageDTO 分页条件
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:view')")
	@ApiOperation("分页查询新闻文章")
	@ApiPageParam
	@PostMapping("/findNewsArticless")
	public PageResultDTO findNewsArticless(@RequestBody PageDTO pageDTO){
		return this.newsArticlesService.findNewsArticless(pageDTO);
	}

	/**
	* 根据ID查询指定的新闻文章
	*
	* @param newsArticlesId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:view')")
	@ApiOperation("根据id查询新闻文章")
	@ApiPageParam
	@PostMapping("/findNewsArticles")
	public NewsArticles findNewsArticles(@RequestParam Long newsArticlesId){
		return this.newsArticlesService.findNewsArticles(newsArticlesId);
	}

	/**
	* 根据ID查询指定的新闻文章(包含外键名称)
	*
	* @param newsArticlesId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:view')")
	@ApiOperation("根据ID查询指定的新闻文章(包含外键名称)")
	@PostMapping("/findNewsArticlesForView")
	public NewsArticles findNewsArticlesForView(@RequestParam Long newsArticlesId){
		return this.newsArticlesService.findNewsArticlesWithForeignName(newsArticlesId);
	}

	/**
	* 根据ID查询指定的新闻文章(包含新闻文章和外键名称)
	*
	* @param newsArticlesId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:view')")
	@ApiOperation("根据ID查询指定的新闻文章(包含新闻文章和外键名称)")
	@PostMapping("/findNewsArticlesForEdit")
	public NewsArticlesEditDto findNewsArticlesForEdit(@RequestParam Long newsArticlesId){
				NewsArticlesEditDto newsArticlesEditDto = new NewsArticlesEditDto();
		newsArticlesEditDto.setNewsArticles(this.newsArticlesService.findNewsArticlesWithForeignName(newsArticlesId));

		this.prepareNewsArticlesEditDto(newsArticlesEditDto);

		return newsArticlesEditDto;
	}

	/**
	* 根据ID查询指定的新闻文章(只提取ID 和 Name)
	*
	* @param newsArticlesId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:view')")
	@ApiOperation("根据ID查询指定的新闻文章(只提取ID 和 Name)")
	@PostMapping("/findNewsArticlessWithIdNameById")
	public NewsArticles findNewsArticlessWithIdNameById(@RequestParam Long newsArticlesId){
	return this.newsArticlesService.findNewsArticlessWithIdNameById(newsArticlesId);
	}

	/**
	* 根据名称查询新闻文章集合(只提取ID 和 Name)
	*
	* @param newsArticlesName 名称
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:view')")
	@ApiOperation("根据名称查询新闻文章集合(只提取ID 和 Name)")
	@PostMapping("/findNewsArticlessWithIdNameByName")
	public List<NewsArticles> findNewsArticlessWithIdNameByName(@RequestParam String newsArticlesName){
	return this.newsArticlesService.findNewsArticlessWithIdNameByName(newsArticlesName);
	}


	/**
	* 创建新的新闻文章
	*
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:add')")
	@ApiOperation("创建新的新闻文章")
	@PostMapping("/createNewsArticles")
	public NewsArticlesEditDto createNewsArticles(){
				NewsArticlesEditDto newsArticlesEditDto = new NewsArticlesEditDto();
		newsArticlesEditDto.setNewsArticles(new NewsArticles());

		this.prepareNewsArticlesEditDto(newsArticlesEditDto);
		return newsArticlesEditDto;
	}

	private void prepareNewsArticlesEditDto(NewsArticlesEditDto newsArticlesEditDto){
																																																			        //TODO: 以下代码可以注释掉，此行代码即时加载所有外键对象，以便选择。如果不在此加载，可以在界面上实现延迟加载。如果外键对象超过 500 行，建议采用延迟加载
		newsArticlesEditDto.setPublisherNamePublishers(this.publisherService.findAllPublishersWithIdName());
													}

	/**
	* 新增保存新闻文章
	*
	* @param newsArticles 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:add')")
	@ApiOperation("新增保存新闻文章")
	@PostMapping("/saveNewsArticles")
	public NewsArticles saveNewsArticles(@RequestBody NewsArticles newsArticles){
		return this.newsArticlesService.saveNewsArticles(newsArticles);
	}

	/**
	* 修改保存新闻文章
	*
	* @param newsArticles 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:update')")
	@ApiOperation("修改保存新闻文章")
	@PostMapping("/updateNewsArticles")
	public NewsArticles updateNewsArticles(@RequestBody NewsArticles newsArticles){
		return this.newsArticlesService.updateNewsArticles(newsArticles);
	}

	/**
	* 根据ID删除新闻文章
	*
	* @param newsArticlesId ID
	*/
	@PreAuthorize("hasAuthority('cms:newsArticles:NewsArticles:remove')")
	@ApiOperation("根据ID删除新闻文章")
	@PostMapping("/deleteNewsArticles")
	public void deleteNewsArticles(@RequestParam Long newsArticlesId){
		this.newsArticlesService.deleteNewsArticles(newsArticlesId);
	}



}

