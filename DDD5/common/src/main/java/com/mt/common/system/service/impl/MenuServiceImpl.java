package com.mt.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mt.common.core.web.PageParam;
import com.mt.common.system.entity.Menu;
import com.mt.common.system.mapper.MenuMapper;
import com.mt.common.system.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单服务实现类
 * Created by wangfan on 2018-12-24 16:10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<Menu> listPage(PageParam<Menu> pageParam) {
        return baseMapper.listPage(pageParam);
    }

    @Override
    public List<Menu> getUserMenu(Long userId, Long menuType) {
        return baseMapper.listByUserId(userId, menuType);
    }

    @Override
    public List<Menu> toMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(toMenuTree(menus, menu.getMenuId()));
                list.add(menu);
            }
        }
        return list;
    }

}
