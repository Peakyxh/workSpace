package com.mt.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mt.common.system.entity.UserRole;

/**
 * 用户角色服务类
 * Created by wangfan on 2018-12-24 16:10
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 查询用户的角色id
     */
    Integer[] getRoleIds(String eid);

}
