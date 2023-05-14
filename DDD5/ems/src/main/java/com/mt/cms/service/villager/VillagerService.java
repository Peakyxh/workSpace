package com.mt.cms.service.villager;

import com.mt.common.core.web.base.PageDTO;
import com.mt.common.core.web.base.PageResultDTO;
import com.mt.cms.entity.villager.Villager;

import java.util.List;

public interface VillagerService {
    /**
     * 根据分页参数查询村民信息集合
     *
     * @param pageDTO 分页条件
     */
    public PageResultDTO findVillagers(PageDTO pageDTO);

    /**
     * 查询全部村民信息集合
     *
     */
    public List<Villager> findAllVillagers();

    /**
     * 根据名称查询村民信息集合(只提取ID 和 Name)
     *
     * @param villagerName 名称
     */
    public List<Villager> findVillagersWithIdNameByName(String villagerName);

    /**
     * 查询所有村民信息集合(只提取ID 和 Name)
     *
     */
    public List<Villager> findAllVillagersWithIdName();

    /**
     * 根据ID查询指定的村民信息(只提取ID 和 Name)
     *
     * @param villagerId Id
     */
    public Villager findVillagersWithIdNameById(Long villagerId);

    /**
     * 根据ID查询指定的村民信息
     *
     * @param villagerId Id
     */
    public Villager findVillager(Long villagerId);

    /**
     * 根据ID查询指定的村民信息(包含外键)
     *
     * @param villagerId Id
     */
    public Villager findVillagerWithForeignName(Long villagerId);

    /**
     * 新增村民信息
     *
     * @param villager 实体对象
     */
    public Villager saveVillager(Villager villager);

    /**
     * 更新村民信息
     *
     * @param villager 实体对象
     */
    public Villager updateVillager(Villager villager);

    /**
     * 根据ID删除村民信息
     *
     * @param villagerId ID
     */
    public void deleteVillager(Long villagerId);
}
