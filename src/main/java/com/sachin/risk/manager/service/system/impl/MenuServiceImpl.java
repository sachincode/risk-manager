package com.sachin.risk.manager.service.system.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sachin.risk.manager.dao.system.MenuMapper;
import com.sachin.risk.manager.model.system.Menu;
import com.sachin.risk.manager.model.system.MenuDto;
import com.sachin.risk.manager.model.system.MenuTree;
import com.sachin.risk.manager.service.system.MenuService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author shicheng.zhang
 * @date 17-3-19
 * @time 下午10:27
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private static final long ROOT_MENU_ID = 0;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenus() {
        return menuMapper.query(new HashMap<String, Object>());
    }

    @Override
    public List<Menu> queryByUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("url", url);
        return menuMapper.query(params);
    }

    @Override
    public MenuTree getMenuTree(Menu curMenu) {
        List<Menu> menus = queryAllMenus();
        if (CollectionUtils.isEmpty(menus)) {
            return new MenuTree();
        }
        MenuTree menuTree = buildMenuTree(transformToDtoList(menus), curMenu);
        sortMenuDtoList(menuTree.getMenuList());
        return menuTree;
    }

    private MenuTree buildMenuTree(List<MenuDto> menuDtoList, Menu curMenu) {
        MenuTree menuTree = new MenuTree();
        Map<Long, MenuDto> menuMap = transformToMap(menuDtoList);
        // 填充菜单父子关系
        for (Map.Entry<Long, MenuDto> entry : menuMap.entrySet()) {
            MenuDto menuDto = entry.getValue();
            if (menuDto.getParentMenuId() != ROOT_MENU_ID) {
                MenuDto parent = menuMap.get(menuDto.getParentMenuId());
                if (parent == null) {
                    logger.error("can not find parent menu. this id: {}, parent id: {}", menuDto.getId(), menuDto.getParentMenuId());
                } else {
                    parent.getSubMenus().add(menuDto);
                }
            }
        }
        // 设置当前展开菜单属性
        if (curMenu != null) {
            MenuDto menuDto = menuMap.get(curMenu.getId());
            if (menuDto != null) {
                menuDto.setActive(true);
                menuTree.getCrumbList().add(menuDto);
                while (menuDto.getParentMenuId() != ROOT_MENU_ID) {
                    menuDto = menuMap.get(menuDto.getParentMenuId());
                    if (menuDto == null) {
                        break;
                    }
                    menuDto.setActive(true);
                    menuTree.getCrumbList().add(0, menuDto);
                }
            }
        }
        // 取出第一级菜单
        List<MenuDto> menus = Lists.newArrayList();
        for (Map.Entry<Long, MenuDto> entry : menuMap.entrySet()) {
            MenuDto menuDto = entry.getValue();
            if (menuDto.getParentMenuId() == ROOT_MENU_ID) {
                menus.add(menuDto);
            }
        }
        menuTree.setMenuList(menus);
        return menuTree;
    }

    private Map<Long, MenuDto> transformToMap(List<MenuDto> menus) {
        Map<Long, MenuDto> menuMap = Maps.newLinkedHashMap();
        for (MenuDto menu : menus) {
            menuMap.put(menu.getId(), menu);
        }
        return menuMap;
    }

    private List<MenuDto> transformToDtoList(List<Menu> menus) {
        List<MenuDto> menuDtoList = Lists.newArrayList();
        for (Menu menu : menus) {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu, menuDto);
            menuDtoList.add(menuDto);
        }
        return menuDtoList;
    }

    private void sortMenu(List<? extends Menu> menus) {
        Collections.sort(menus, new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                if (o1.getSortNumber() == o2.getSortNumber()) {
                    return o1.getId() > o2.getId() ? 1 : -1;
                }
                return o1.getSortNumber() > o2.getSortNumber() ? 1 : -1;
            }
        });
    }

    private void sortMenuDtoList(List<MenuDto> menuDtoList) {
        sortMenu(menuDtoList);
        for (MenuDto menuDto : menuDtoList) {
            if (CollectionUtils.isNotEmpty(menuDto.getSubMenus())) {
                sortMenuDtoList(menuDto.getSubMenus());
            }
        }
    }
}
