package com.sachin.risk.manager.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author shicheng.zhang
 * @date 17-3-24
 * @time 上午11:08
 * @Description:
 */
public class CookieUtil {

    public static final String USERNAME = "username";
    public static final String ROOT_PATH = "/";

    /**
     * 获取cookie中的值
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse response, String name, String value, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(1));
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String name, String path) {
        Cookie userCookie = new Cookie(name, StringUtils.EMPTY);
        userCookie.setPath(path);
        userCookie.setMaxAge(0);
        response.addCookie(userCookie);
    }

    public static String getLoginUsername(HttpServletRequest request) {
        return getCookieValue(request, USERNAME);
    }

    public static void login(HttpServletRequest request, HttpServletResponse response, String username) {
        addCookie(response, USERNAME, username, ROOT_PATH);
        request.setAttribute(USERNAME, username);
    }

    public static void logout(HttpServletResponse response) {
        removeCookie(response, USERNAME, ROOT_PATH);
    }

    public static boolean isLogin(HttpServletRequest request) {
        String username = getLoginUsername(request);
        return !StringUtils.isBlank(username);
    }
}
