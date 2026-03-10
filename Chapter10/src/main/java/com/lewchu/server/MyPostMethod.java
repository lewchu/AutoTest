package com.lewchu.server;

import com.lewchu.bean.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "POST 管理", description = "所有的 POST 请求方法")
public class MyPostMethod {
    
    // 这个变量用来保存获取的cookie
    private static Cookie cookie;
    
    // 场景：用户登陆成功获取到cookie，并保存，再访问列表接口获取商品列表数据
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Operation(summary = "用户登陆接口", description = "用户登陆成功获取到cookie，并保存")
    public String login(HttpServletResponse  response, @RequestParam(value = "userName", required = true) String username, @RequestParam(value = "pwd", required = true) String password) { 
        if (username.equals("admin") && password.equals("123456")) {
            Cookie cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登陆成功！";
        }
        return "用户名或者密码错误！";
    }
    
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @Operation(summary = "获取用户列表", description = "获取用户列表")
    public User getUserList(HttpServletRequest request, @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        // 验证cookies是否合法
        for (Cookie c : cookies) {
            if ("login".equals(c.getName()) && "true".equals(c.getValue())) {
                User u = new User();
                u.setName("lew");
                u.setAge(18);
                u.setSex("男");
                return u;
            }
        }
        return null;
    }
}
