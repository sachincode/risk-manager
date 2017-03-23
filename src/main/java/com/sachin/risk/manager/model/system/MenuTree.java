package com.sachin.risk.manager.model.system;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shicheng.zhang
 * @date 17-3-20
 * @time 下午1:04
 * @Description:
 */
public class MenuTree {

    private List<MenuDto> menuList = new ArrayList<>();

    private List<MenuDto> crumbList = new ArrayList<>();

    public List<MenuDto> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuDto> menuList) {
        this.menuList = menuList;
    }

    public List<MenuDto> getCrumbList() {
        return crumbList;
    }

    public void setCrumbList(List<MenuDto> crumbList) {
        this.crumbList = crumbList;
    }
}
