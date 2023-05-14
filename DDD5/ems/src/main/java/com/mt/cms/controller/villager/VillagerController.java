

package com.mt.cms.controller.villager;

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



import com.mt.cms.dto.villager.VillagerEditDto;
import com.mt.cms.entity.villager.Villager;
import com.mt.cms.service.villager.VillagerService;
											

@Api(tags = "村民信息")
@RestController
@RequestMapping("/api/cms/villager/Villager")
@CrossOrigin(allowCredentials = "true")
public class VillagerController {
private static Logger logger = LoggerFactory.getLogger(VillagerController.class);



																																				@Autowired private VillagerService villagerService;
	
	/**
	* 根据分页参数查询村民信息集合
	*
	* @param pageDTO 分页条件
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:view')")
	@ApiOperation("分页查询村民信息")
	@ApiPageParam
	@PostMapping("/findVillagers")
	public PageResultDTO findVillagers(@RequestBody PageDTO pageDTO){
		return this.villagerService.findVillagers(pageDTO);
	}

	/**
	* 根据ID查询指定的村民信息
	*
	* @param villagerId Id
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:view')")
	@ApiOperation("根据id查询村民信息")
	@ApiPageParam
	@PostMapping("/findVillager")
	public Villager findVillager(@RequestParam Long villagerId){
		return this.villagerService.findVillager(villagerId);
	}

	/**
	* 根据ID查询指定的村民信息(包含外键名称)
	*
	* @param villagerId Id
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:view')")
	@ApiOperation("根据ID查询指定的村民信息(包含外键名称)")
	@PostMapping("/findVillagerForView")
	public Villager findVillagerForView(@RequestParam Long villagerId){
		return this.villagerService.findVillagerWithForeignName(villagerId);
	}

	/**
	* 根据ID查询指定的村民信息(包含村民信息和外键名称)
	*
	* @param villagerId Id
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:view')")
	@ApiOperation("根据ID查询指定的村民信息(包含村民信息和外键名称)")
	@PostMapping("/findVillagerForEdit")
	public VillagerEditDto findVillagerForEdit(@RequestParam Long villagerId){
				VillagerEditDto villagerEditDto = new VillagerEditDto();
		villagerEditDto.setVillager(this.villagerService.findVillagerWithForeignName(villagerId));

		this.prepareVillagerEditDto(villagerEditDto);

		return villagerEditDto;
	}

	/**
	* 根据ID查询指定的村民信息(只提取ID 和 Name)
	*
	* @param villagerId Id
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:view')")
	@ApiOperation("根据ID查询指定的村民信息(只提取ID 和 Name)")
	@PostMapping("/findVillagersWithIdNameById")
	public Villager findVillagersWithIdNameById(@RequestParam Long villagerId){
	return this.villagerService.findVillagersWithIdNameById(villagerId);
	}

	/**
	* 根据名称查询村民信息集合(只提取ID 和 Name)
	*
	* @param villagerName 名称
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:view')")
	@ApiOperation("根据名称查询村民信息集合(只提取ID 和 Name)")
	@PostMapping("/findVillagersWithIdNameByName")
	public List<Villager> findVillagersWithIdNameByName(@RequestParam String villagerName){
	return this.villagerService.findVillagersWithIdNameByName(villagerName);
	}


	/**
	* 创建新的村民信息
	*
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:add')")
	@ApiOperation("创建新的村民信息")
	@PostMapping("/createVillager")
	public VillagerEditDto createVillager(){
				VillagerEditDto villagerEditDto = new VillagerEditDto();
		villagerEditDto.setVillager(new Villager());

		this.prepareVillagerEditDto(villagerEditDto);
		return villagerEditDto;
	}

	private void prepareVillagerEditDto(VillagerEditDto villagerEditDto){
																																			}

	/**
	* 新增保存村民信息
	*
	* @param villager 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:add')")
	@ApiOperation("新增保存村民信息")
	@PostMapping("/saveVillager")
	public Villager saveVillager(@RequestBody Villager villager){
		return this.villagerService.saveVillager(villager);
	}

	/**
	* 修改保存村民信息
	*
	* @param villager 实体对象
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:update')")
	@ApiOperation("修改保存村民信息")
	@PostMapping("/updateVillager")
	public Villager updateVillager(@RequestBody Villager villager){
		return this.villagerService.updateVillager(villager);
	}

	/**
	* 根据ID删除村民信息
	*
	* @param villagerId ID
	*/
	@PreAuthorize("hasAuthority('cms:villager:Villager:remove')")
	@ApiOperation("根据ID删除村民信息")
	@PostMapping("/deleteVillager")
	public void deleteVillager(@RequestParam Long villagerId){
		this.villagerService.deleteVillager(villagerId);
	}



}

