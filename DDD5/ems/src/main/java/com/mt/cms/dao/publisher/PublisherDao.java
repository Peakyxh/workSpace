package com.mt.cms.dao.publisher;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.publisher.Publisher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
@Component(value = "publisherDao")
public interface PublisherDao {

    /**
    * 根据分页参数查询发布者信息集合
    *
    * @param pageDTO 分页条件
    */
    public List<Publisher> findPublishers(PageDTO pageDTO);

    /**
    * 查询全部发布者信息集合
    *
    */
    public List<Publisher> findAllPublishers();

    /**
    * 查询所有发布者信息集合(只提取ID 和 Name)
    *
    */
    public List<Publisher> findAllPublishersWithIdName();

    /**
    * 根据名称查询发布者信息集合(只提取ID 和 Name)
    *
    * @param publisherName 名称
    */
    public List<Publisher> findPublishersWithIdNameByName(@Param("publisherName") String publisherName);

    /**
    * 根据ID查询指定的发布者信息(只提取ID 和 Name)
    *
    * @param publisherId Id
    */
    public Publisher findPublishersWithIdNameById(@Param(" publisherId") Long publisherId);

    /**
    * 根据分页参数查询发布者信息集合的数量
    *
    * @param pageDTO 分页条件
    */
    public Long findPublisherTotalCount(PageDTO pageDTO);

    /**
    * 根据ID查询指定的发布者信息
    *
    * @param publisherId Id
    */
    public Publisher findPublisher(@Param("publisherId") Long publisherId);

    /**
    * 根据ID查询指定的发布者信息(包含外键)
    *
    * @param publisherId Id
    */
    public Publisher findPublisherWithForeignName(@Param("publisherId") Long publisherId);

    /**
    * 新增发布者信息
    *
    * @param publisher 实体对象
    */
    public Long savePublisher(Publisher publisher);

    /**
    * 更新发布者信息
    *
    * @param publisher 实体对象
    */
    public Long updatePublisher(Publisher publisher);

    /**
    * 根据ID删除发布者信息
    *
    * @param publisherId ID
    */
    public Long deletePublisher(@Param("publisherId") Long publisherId);
}
