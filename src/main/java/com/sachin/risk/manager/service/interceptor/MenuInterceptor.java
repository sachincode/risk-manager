package com.sachin.risk.manager.service.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sachin.risk.manager.model.system.Menu;
import com.sachin.risk.manager.model.system.MenuTree;
import com.sachin.risk.manager.service.system.MenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @author shicheng.zhang
 * @date 17-3-20
 * @time 上午10:37
 * @Description:
 */
public class MenuInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private MenuService menuService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        if (modelAndView != null) {
            List<Menu> menus = menuService.queryByUrl(request.getRequestURI());
            Menu menu = null;
            if (CollectionUtils.isNotEmpty(menus)) {
                menu = menus.get(0);
                modelAndView.addObject("page_title", menu.getMenuName());
            }
            MenuTree menuTree = menuService.getMenuTree(menu);
            modelAndView.addObject("sys_menu_list", menuTree.getMenuList());
            modelAndView.addObject("sys_crumb_list", menuTree.getCrumbList());
        }
    }
}