package com.sachin.risk.manager.model.system;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shicheng.zhang
 * @date 17-3-19
 * @time 下午10:42
 * @Description:
 */
public class MenuDto extends Menu {

    private List<MenuDto> subMenus = new ArrayList<>();

    private boolean active;

    public List<MenuDto> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuDto> subMenus) {
        this.subMenus = subMenus;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
