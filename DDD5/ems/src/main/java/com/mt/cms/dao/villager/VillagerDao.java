package com.mt.cms.dao.villager;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.villager.Villager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
@Component(value = "villagerDao")
public interface VillagerDao {

    /**
    * 根据分页参数查询村民信息集合
    *
    * @param pageDTO 分页条件
    */
    public List<Villager> findVillagers(PageDTO pageDTO);

    /**
    * 查询全部村民信息集合
    *
    */
    public List<Villager> findAllVillagers();

    /**
    * 查询所有村民信息集合(只提取ID 和 Name)
    *
    */
    public List<Villager> findAllVillagersWithIdName();

    /**
    * 根据名称查询村民信息集合(只提取ID 和 Name)
    *
    * @param villagerName 名称
    */
    public List<Villager> findVillagersWithIdNameByName(@Param("villagerName") String villagerName);

    /**
    * 根据ID查询指定的村民信息(只提取ID 和 Name)
    *
    * @param villagerId Id
    */
    public Villager findVillagersWithIdNameById(@Param(" villagerId") Long villagerId);

    /**
    * 根据分页参数查询村民信息集合的数量
    *
    * @param pageDTO 分页条件
    */
    public Long findVillagerTotalCount(PageDTO pageDTO);

    /**
    * 根据ID查询指定的村民信息
    *
    * @param villagerId Id
    */
    public Villager findVillager(@Param("villagerId") Long villagerId);

    /**
    * 根据ID查询指定的村民信息(包含外键)
    *
    * @param villagerId Id
    */
    public Villager findVillagerWithForeignName(@Param("villagerId") Long villagerId);

    /**
    * 新增村民信息
    *
    * @param villager 实体对象
    */
    public Long saveVillager(Villager villager);

    /**
    * 更新村民信息
    *
    * @param villager 实体对象
    */
    public Long updateVillager(Villager villager);

    /**
    * 根据ID删除村民信息
    *
    * @param villagerId ID
    */
    public Long deleteVillager(@Param("villagerId") Long villagerId);
}
