package com.lewchu.controller;

import com.lewchu.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Demo", description = "这是我第一个版本的Demo")
public class Demo {
    
    // 首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @Operation(summary = "获取用户数量", description = "这是获取用户数量的接口")
    public int getUserCount() {
        return sqlSessionTemplate.selectOne("getUserCount");
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @Operation(summary = "添加用户", description = "这是添加用户的接口")
    public int addUser(@RequestBody User user) {
        return sqlSessionTemplate.insert("addUser", user);
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @Operation(summary = "更新用户", description = "这是更新用户的接口")
    public int updateUser(@RequestBody User user) {
        return sqlSessionTemplate.update("updateUser", user);
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @Operation(summary = "删除用户", description = "这是删除用户的接口")
    public int deleteUser(@RequestParam int id) {
        return sqlSessionTemplate.delete("deleteUser", id);
    }
}
