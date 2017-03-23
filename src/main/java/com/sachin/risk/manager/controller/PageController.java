package com.sachin.risk.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author shicheng.zhang
 * @date 17-1-17
 * @time 下午2:06
 * @Description:
 */
@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = { "/", "/index.do" })
    public ModelAndView index(HttpServletRequest request) {
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        request.setAttribute("url", handlerMethods);
        return new ModelAndView("hello/hello");
    }

    @RequestMapping(value = "/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/index.do";
    }

    @RequestMapping(value = "/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("用户退出登录");
        return "redirect:/login.do";
    }

    @RequestMapping(value = "/error/404.do")
    public ModelAndView page404() {
        return new ModelAndView("error/404");
    }
}
