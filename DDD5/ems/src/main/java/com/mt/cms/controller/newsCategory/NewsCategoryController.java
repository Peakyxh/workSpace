

package com.mt.cms.controller.newsCategory;

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



import com.mt.cms.dto.newsCategory.NewsCategoryEditDto;
import com.mt.cms.entity.newsCategory.NewsCategory;
import com.mt.cms.service.newsCategory.NewsCategoryService;
							

@Api(tags = "新闻类别")
@RestController
@RequestMapping("/api/cms/newsCategory/NewsCategory")
@CrossOrigin(allowCredentials = "true")
public class NewsCategoryController {
private static Logger logger = LoggerFactory.getLogger(NewsCategoryController.class);



																								@Autowired private NewsCategoryService newsCategoryService;
	
	/**
	* 根据分页参数查询新闻类别集合
	*
	* @param pageDTO 分页条件
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:view')")
	@ApiOperation("分页查询新闻类别")
	@ApiPageParam
	@PostMapping("/findNewsCategorys")
	public PageResultDTO findNewsCategorys(@RequestBody PageDTO pageDTO){
		return this.newsCategoryService.findNewsCategorys(pageDTO);
	}

	/**
	* 根据ID查询指定的新闻类别
	*
	* @param newsCategoryId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:view')")
	@ApiOperation("根据id查询新闻类别")
	@ApiPageParam
	@PostMapping("/findNewsCategory")
	public NewsCategory findNewsCategory(@RequestParam Long newsCategoryId){
		return this.newsCategoryService.findNewsCategory(newsCategoryId);
	}

	/**
	* 根据ID查询指定的新闻类别(包含外键名称)
	*
	* @param newsCategoryId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:view')")
	@ApiOperation("根据ID查询指定的新闻类别(包含外键名称)")
	@PostMapping("/findNewsCategoryForView")
	public NewsCategory findNewsCategoryForView(@RequestParam Long newsCategoryId){
		return this.newsCategoryService.findNewsCategoryWithForeignName(newsCategoryId);
	}

	/**
	* 根据ID查询指定的新闻类别(包含新闻类别和外键名称)
	*
	* @param newsCategoryId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:view')")
	@ApiOperation("根据ID查询指定的新闻类别(包含新闻类别和外键名称)")
	@PostMapping("/findNewsCategoryForEdit")
	public NewsCategoryEditDto findNewsCategoryForEdit(@RequestParam Long newsCategoryId){
				NewsCategoryEditDto newsCategoryEditDto = new NewsCategoryEditDto();
		newsCategoryEditDto.setNewsCategory(this.newsCategoryService.findNewsCategoryWithForeignName(newsCategoryId));

		this.prepareNewsCategoryEditDto(newsCategoryEditDto);

		return newsCategoryEditDto;
	}

	/**
	* 根据ID查询指定的新闻类别(只提取ID 和 Name)
	*
	* @param newsCategoryId Id
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:view')")
	@ApiOperation("根据ID查询指定的新闻类别(只提取ID 和 Name)")
	@PostMapping("/findNewsCategorysWithIdNameById")
	public NewsCategory findNewsCategorysWithIdNameById(@RequestParam Long newsCategoryId){
	return this.newsCategoryService.findNewsCategorysWithIdNameById(newsCategoryId);
	}

	/**
	* 根据名称查询新闻类别集合(只提取ID 和 Name)
	*
	* @param newsCategoryName 名称
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:view')")
	@ApiOperation("根据名称查询新闻类别集合(只提取ID 和 Name)")
	@PostMapping("/findNewsCategorysWithIdNameByName")
	public List<NewsCategory> findNewsCategorysWithIdNameByName(@RequestParam String newsCategoryName){
	return this.newsCategoryService.findNewsCategorysWithIdNameByName(newsCategoryName);
	}


	/**
	* 创建新的新闻类别
	*
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:add')")
	@ApiOperation("创建新的新闻类别")
	@PostMapping("/createNewsCategory")
	public NewsCategoryEditDto createNewsCategory(){
				NewsCategoryEditDto newsCategoryEditDto = new NewsCategoryEditDto();
		newsCategoryEditDto.setNewsCategory(new NewsCategory());

		this.prepareNewsCategoryEditDto(newsCategoryEditDto);
		return newsCategoryEditDto;
	}

	private void prepareNewsCategoryEditDto(NewsCategoryEditDto newsCategoryEditDto){
																							}

	/**
	* 新增保存新闻类别
	*
	* @param newsCategory 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:add')")
	@ApiOperation("新增保存新闻类别")
	@PostMapping("/saveNewsCategory")
	public NewsCategory saveNewsCategory(@RequestBody NewsCategory newsCategory){
		return this.newsCategoryService.saveNewsCategory(newsCategory);
	}

	/**
	* 修改保存新闻类别
	*
	* @param newsCategory 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:update')")
	@ApiOperation("修改保存新闻类别")
	@PostMapping("/updateNewsCategory")
	public NewsCategory updateNewsCategory(@RequestBody NewsCategory newsCategory){
		return this.newsCategoryService.updateNewsCategory(newsCategory);
	}

	/**
	* 根据ID删除新闻类别
	*
	* @param newsCategoryId ID
	*/
	@PreAuthorize("hasAuthority('cms:newsCategory:NewsCategory:remove')")
	@ApiOperation("根据ID删除新闻类别")
	@PostMapping("/deleteNewsCategory")
	public void deleteNewsCategory(@RequestParam Long newsCategoryId){
		this.newsCategoryService.deleteNewsCategory(newsCategoryId);
	}



}

