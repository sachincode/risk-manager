package com.sachin.risk.manager.controller;

import javax.annotation.Resource;

import com.sachin.risk.manager.service.system.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author shicheng.zhang
 * @date 16-7-25
 * @time 下午10:03
 * @Description:
 */
@Controller
public class HelloController {

    @Resource
    private UserService userService;

    @RequestMapping("ex.do")
    public ModelAndView ex() {
        throw new RuntimeException("测试异常");
    }

    @RequestMapping("sayhi.do")
    @ResponseBody
    public Object sayHi(@RequestParam("name") String name) {
        return "hello, " + name;
    }

    @RequestMapping("user.do")
    @ResponseBody
    public Object user(@RequestParam("name") String name) {
        return userService.queryByUsername(name);
    }
}
