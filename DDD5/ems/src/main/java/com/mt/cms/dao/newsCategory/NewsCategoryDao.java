package com.mt.cms.dao.newsCategory;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.newsCategory.NewsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
@Component(value = "newsCategoryDao")
public interface NewsCategoryDao {

    /**
    * 根据分页参数查询新闻类别集合
    *
    * @param pageDTO 分页条件
    */
    public List<NewsCategory> findNewsCategorys(PageDTO pageDTO);

    /**
    * 查询全部新闻类别集合
    *
    */
    public List<NewsCategory> findAllNewsCategorys();

    /**
    * 查询所有新闻类别集合(只提取ID 和 Name)
    *
    */
    public List<NewsCategory> findAllNewsCategorysWithIdName();

    /**
    * 根据名称查询新闻类别集合(只提取ID 和 Name)
    *
    * @param newsCategoryName 名称
    */
    public List<NewsCategory> findNewsCategorysWithIdNameByName(@Param("newsCategoryName") String newsCategoryName);

    /**
    * 根据ID查询指定的新闻类别(只提取ID 和 Name)
    *
    * @param newsCategoryId Id
    */
    public NewsCategory findNewsCategorysWithIdNameById(@Param(" newsCategoryId") Long newsCategoryId);

    /**
    * 根据分页参数查询新闻类别集合的数量
    *
    * @param pageDTO 分页条件
    */
    public Long findNewsCategoryTotalCount(PageDTO pageDTO);

    /**
    * 根据ID查询指定的新闻类别
    *
    * @param newsCategoryId Id
    */
    public NewsCategory findNewsCategory(@Param("newsCategoryId") Long newsCategoryId);

    /**
    * 根据ID查询指定的新闻类别(包含外键)
    *
    * @param newsCategoryId Id
    */
    public NewsCategory findNewsCategoryWithForeignName(@Param("newsCategoryId") Long newsCategoryId);

    /**
    * 新增新闻类别
    *
    * @param newsCategory 实体对象
    */
    public Long saveNewsCategory(NewsCategory newsCategory);

    /**
    * 更新新闻类别
    *
    * @param newsCategory 实体对象
    */
    public Long updateNewsCategory(NewsCategory newsCategory);

    /**
    * 根据ID删除新闻类别
    *
    * @param newsCategoryId ID
    */
    public Long deleteNewsCategory(@Param("newsCategoryId") Long newsCategoryId);
}
