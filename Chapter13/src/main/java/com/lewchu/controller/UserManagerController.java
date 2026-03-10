package com.lewchu.controller;

import com.lewchu.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "用户管理接口", description = "用户管理接口")
@RestController
public class UserManagerController {

    private static final Logger log = LogManager.getLogger(UserManagerController.class);
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    @Operation(summary = "用户登陆接口", description = "用户登陆接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public Boolean login(HttpServletResponse response, @RequestBody User user) {
        int i = sqlSessionTemplate.selectOne("login", user);
        log.info("查询到的结果是：{}", i);
        Cookie cookie = new Cookie("token", "1122334455");
        response.addCookie(cookie);
        if (i == 1) {
            log.info("登陆成功，登陆的用户是：{}", user.getName());
            return true;
        }
        return false;
    }
    
    @Operation(summary = "用户添加接口", description = "用户添加接口")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request, @RequestBody User user) {
        boolean flag = checkCookie(request);
        if (flag) {
            int i = sqlSessionTemplate.insert("addUser", user);
            log.info("添加结果是：{}", i);
            if (i == 1) {
                log.info("添加用户成功，添加的用户是：{}", user.getName());
                return true;
            }
            log.info("添加用户失败，添加的用户是：{}", user.getName());
            return false;
        }
        log.info("用户未登陆");
        return false;
    }

    @Operation(summary = "用户信息查询接口", description = "用户信息查询接口")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public User getUserInfo(HttpServletRequest request, @RequestBody User user) {
        boolean flag = checkCookie(request);
        if (flag) {
            User userInfo = sqlSessionTemplate.selectOne("getUserInfo", user.getId());
            log.info("查询结果是：{}", userInfo);
            return userInfo;
        }
        log.info("用户未登陆");
        return null;
    }
    
    @Operation(summary = "用户列表查询接口", description = "用户列表查询接口")
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request, @RequestBody User user) {
        boolean flag = checkCookie(request);
        if (flag) {
            List<User> userList = sqlSessionTemplate.selectList("getUserList", user);
            log.info("查询结果数量是：{}", userList.size());
            return userList;
        }
        log.info("用户未登陆");
        return null;
    }
    
    @Operation(summary = "用户信息更新接口", description = "用户信息更新接口")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request, @RequestBody User user) {
        boolean flag = checkCookie(request);
        if (flag) {
            int i = sqlSessionTemplate.update("updateUser", user);
            log.info("更新结果是：{}", i);
            return i;
        }
        log.info("用户未登陆");
        return 0;
    }
    
    private boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.info("没有cookies");
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName()) && "1122334455".equals(cookie.getValue())) {
                return true;
            }
        }
        return false;
    }
}
