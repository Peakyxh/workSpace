package com.mt.cms.service.newsArticles;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.newsArticles.NewsArticles;

import java.util.List;

public interface NewsArticlesService {
    /**
     * 根据分页参数查询新闻文章集合
     *
     * @param pageDTO 分页条件
     */
    public PageResultDTO findNewsArticless(PageDTO pageDTO);

    /**
     * 查询全部新闻文章集合
     *
     */
    public List<NewsArticles> findAllNewsArticless();

    /**
     * 根据名称查询新闻文章集合(只提取ID 和 Name)
     *
     * @param newsArticlesName 名称
     */
    public List<NewsArticles> findNewsArticlessWithIdNameByName(String newsArticlesName);

    /**
     * 查询所有新闻文章集合(只提取ID 和 Name)
     *
     */
    public List<NewsArticles> findAllNewsArticlessWithIdName();

    /**
     * 根据ID查询指定的新闻文章(只提取ID 和 Name)
     *
     * @param newsArticlesId Id
     */
    public NewsArticles findNewsArticlessWithIdNameById(Long newsArticlesId);

    /**
     * 根据ID查询指定的新闻文章
     *
     * @param newsArticlesId Id
     */
    public NewsArticles findNewsArticles(Long newsArticlesId);

    /**
     * 根据ID查询指定的新闻文章(包含外键)
     *
     * @param newsArticlesId Id
     */
    public NewsArticles findNewsArticlesWithForeignName(Long newsArticlesId);

    /**
     * 新增新闻文章
     *
     * @param newsArticles 实体对象
     */
    public NewsArticles saveNewsArticles(NewsArticles newsArticles);

    /**
     * 更新新闻文章
     *
     * @param newsArticles 实体对象
     */
    public NewsArticles updateNewsArticles(NewsArticles newsArticles);

    /**
     * 根据ID删除新闻文章
     *
     * @param newsArticlesId ID
     */
    public void deleteNewsArticles(Long newsArticlesId);
}
