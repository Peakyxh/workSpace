package com.mt.cms.dao.newsComment;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.newsComment.NewsComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
@Component(value = "newsCommentDao")
public interface NewsCommentDao {

    /**
    * 根据分页参数查询新闻评论集合
    *
    * @param pageDTO 分页条件
    */
    public List<NewsComment> findNewsComments(PageDTO pageDTO);

    /**
    * 查询全部新闻评论集合
    *
    */
    public List<NewsComment> findAllNewsComments();

    /**
    * 查询所有新闻评论集合(只提取ID 和 Name)
    *
    */
    public List<NewsComment> findAllNewsCommentsWithIdName();

    /**
    * 根据名称查询新闻评论集合(只提取ID 和 Name)
    *
    * @param newsCommentName 名称
    */
    public List<NewsComment> findNewsCommentsWithIdNameByName(@Param("newsCommentName") String newsCommentName);

    /**
    * 根据ID查询指定的新闻评论(只提取ID 和 Name)
    *
    * @param newsCommentId Id
    */
    public NewsComment findNewsCommentsWithIdNameById(@Param(" newsCommentId") Long newsCommentId);

    /**
    * 根据分页参数查询新闻评论集合的数量
    *
    * @param pageDTO 分页条件
    */
    public Long findNewsCommentTotalCount(PageDTO pageDTO);

    /**
    * 根据ID查询指定的新闻评论
    *
    * @param newsCommentId Id
    */
    public NewsComment findNewsComment(@Param("newsCommentId") Long newsCommentId);

    /**
    * 根据ID查询指定的新闻评论(包含外键)
    *
    * @param newsCommentId Id
    */
    public NewsComment findNewsCommentWithForeignName(@Param("newsCommentId") Long newsCommentId);

    /**
    * 新增新闻评论
    *
    * @param newsComment 实体对象
    */
    public Long saveNewsComment(NewsComment newsComment);

    /**
    * 更新新闻评论
    *
    * @param newsComment 实体对象
    */
    public Long updateNewsComment(NewsComment newsComment);

    /**
    * 根据ID删除新闻评论
    *
    * @param newsCommentId ID
    */
    public Long deleteNewsComment(@Param("newsCommentId") Long newsCommentId);
}
