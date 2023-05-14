package com.mt.cms.service.newsComment;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.newsComment.NewsComment;

import java.util.List;

public interface NewsCommentService {
    /**
     * 根据分页参数查询新闻评论集合
     *
     * @param pageDTO 分页条件
     */
    public PageResultDTO findNewsComments(PageDTO pageDTO);

    /**
     * 查询全部新闻评论集合
     *
     */
    public List<NewsComment> findAllNewsComments();

    /**
     * 根据名称查询新闻评论集合(只提取ID 和 Name)
     *
     * @param newsCommentName 名称
     */
    public List<NewsComment> findNewsCommentsWithIdNameByName(String newsCommentName);

    /**
     * 查询所有新闻评论集合(只提取ID 和 Name)
     *
     */
    public List<NewsComment> findAllNewsCommentsWithIdName();

    /**
     * 根据ID查询指定的新闻评论(只提取ID 和 Name)
     *
     * @param newsCommentId Id
     */
    public NewsComment findNewsCommentsWithIdNameById(Long newsCommentId);

    /**
     * 根据ID查询指定的新闻评论
     *
     * @param newsCommentId Id
     */
    public NewsComment findNewsComment(Long newsCommentId);

    /**
     * 根据ID查询指定的新闻评论(包含外键)
     *
     * @param newsCommentId Id
     */
    public NewsComment findNewsCommentWithForeignName(Long newsCommentId);

    /**
     * 新增新闻评论
     *
     * @param newsComment 实体对象
     */
    public NewsComment saveNewsComment(NewsComment newsComment);

    /**
     * 更新新闻评论
     *
     * @param newsComment 实体对象
     */
    public NewsComment updateNewsComment(NewsComment newsComment);

    /**
     * 根据ID删除新闻评论
     *
     * @param newsCommentId ID
     */
    public void deleteNewsComment(Long newsCommentId);
}
