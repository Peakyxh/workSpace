package com.mt.cms.service.newsCategory;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.newsCategory.NewsCategory;

import java.util.List;

public interface NewsCategoryService {
    /**
     * 根据分页参数查询新闻类别集合
     *
     * @param pageDTO 分页条件
     */
    public PageResultDTO findNewsCategorys(PageDTO pageDTO);

    /**
     * 查询全部新闻类别集合
     *
     */
    public List<NewsCategory> findAllNewsCategorys();

    /**
     * 根据名称查询新闻类别集合(只提取ID 和 Name)
     *
     * @param newsCategoryName 名称
     */
    public List<NewsCategory> findNewsCategorysWithIdNameByName(String newsCategoryName);

    /**
     * 查询所有新闻类别集合(只提取ID 和 Name)
     *
     */
    public List<NewsCategory> findAllNewsCategorysWithIdName();

    /**
     * 根据ID查询指定的新闻类别(只提取ID 和 Name)
     *
     * @param newsCategoryId Id
     */
    public NewsCategory findNewsCategorysWithIdNameById(Long newsCategoryId);

    /**
     * 根据ID查询指定的新闻类别
     *
     * @param newsCategoryId Id
     */
    public NewsCategory findNewsCategory(Long newsCategoryId);

    /**
     * 根据ID查询指定的新闻类别(包含外键)
     *
     * @param newsCategoryId Id
     */
    public NewsCategory findNewsCategoryWithForeignName(Long newsCategoryId);

    /**
     * 新增新闻类别
     *
     * @param newsCategory 实体对象
     */
    public NewsCategory saveNewsCategory(NewsCategory newsCategory);

    /**
     * 更新新闻类别
     *
     * @param newsCategory 实体对象
     */
    public NewsCategory updateNewsCategory(NewsCategory newsCategory);

    /**
     * 根据ID删除新闻类别
     *
     * @param newsCategoryId ID
     */
    public void deleteNewsCategory(Long newsCategoryId);
}
