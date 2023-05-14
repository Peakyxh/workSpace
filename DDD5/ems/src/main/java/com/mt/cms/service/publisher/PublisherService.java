package com.mt.cms.service.publisher;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.publisher.Publisher;

import java.util.List;

public interface PublisherService {
    /**
     * 根据分页参数查询发布者信息集合
     *
     * @param pageDTO 分页条件
     */
    public PageResultDTO findPublishers(PageDTO pageDTO);

    /**
     * 查询全部发布者信息集合
     *
     */
    public List<Publisher> findAllPublishers();

    /**
     * 根据名称查询发布者信息集合(只提取ID 和 Name)
     *
     * @param publisherName 名称
     */
    public List<Publisher> findPublishersWithIdNameByName(String publisherName);

    /**
     * 查询所有发布者信息集合(只提取ID 和 Name)
     *
     */
    public List<Publisher> findAllPublishersWithIdName();

    /**
     * 根据ID查询指定的发布者信息(只提取ID 和 Name)
     *
     * @param publisherId Id
     */
    public Publisher findPublishersWithIdNameById(Long publisherId);

    /**
     * 根据ID查询指定的发布者信息
     *
     * @param publisherId Id
     */
    public Publisher findPublisher(Long publisherId);

    /**
     * 根据ID查询指定的发布者信息(包含外键)
     *
     * @param publisherId Id
     */
    public Publisher findPublisherWithForeignName(Long publisherId);

    /**
     * 新增发布者信息
     *
     * @param publisher 实体对象
     */
    public Publisher savePublisher(Publisher publisher);

    /**
     * 更新发布者信息
     *
     * @param publisher 实体对象
     */
    public Publisher updatePublisher(Publisher publisher);

    /**
     * 根据ID删除发布者信息
     *
     * @param publisherId ID
     */
    public void deletePublisher(Long publisherId);
}
