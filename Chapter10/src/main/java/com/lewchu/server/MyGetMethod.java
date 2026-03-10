package com.lewchu.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "GET 管理", description = "所有的 GET 请求方法")
public class MyGetMethod {
    
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @Operation(summary = "获取cookies", description = "这是获取cookies的接口")
    public String getCookies(HttpServletResponse response) {
        // HttpServletRequest对象，装请求信息的类
        // HttpServletResponse对象，装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你成功获取cookies";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies才能访问的get请求
     */
    @RequestMapping(value = "/getWithCookies", method = RequestMethod.GET)
    @Operation(summary = "需要携带cookies访问", description = "这是需要携带cookies访问的接口")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "恭喜你访问成功！";
            }
        }
        return "你必须携带cookies信息来";
    }

    /**
     * 第一种需要携带请求参数才能访问的get请求
     */
    @RequestMapping(value = "/getWithParam", method = RequestMethod.GET)
    @Operation(summary = "需要携带参数才能访问的get请求", description = "这是需要携带参数才能访问的get请求")
    public Map<String, Integer> getList(@RequestParam int start, @RequestParam int end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("干脆面", 1);
        myList.put("衬衫", 300);
        return myList;
    }

    /**
     * 第二种需要携带访问参数才能访问的get请求
     */
    @RequestMapping(value = "/getWithParam2/{start}/{end}", method = RequestMethod.GET)
    @Operation(summary = "需要携带参数才能访问的get请求2", description = "这是需要携带参数才能访问的get请求2")
    public Map<String, Integer> getList2(@PathVariable int start, @PathVariable int end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("包", 200);
        myList.put("可乐", 10);
        myList.put("苹果", 300);
        return myList;
    }
}
