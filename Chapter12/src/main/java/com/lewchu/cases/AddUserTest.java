package com.lewchu.cases;

import com.alibaba.fastjson2.JSONObject;
import com.lewchu.config.TestConfig;
import com.lewchu.model.AddUserCase;
import com.lewchu.model.User;
import com.lewchu.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddUserTest {
    
    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddUserCase addUsercase = sqlSession.selectOne("addUserCase", 1);
        System.out.println(TestConfig.cookieStore.getCookies());
        System.out.println(addUsercase.toString());
        System.out.println(TestConfig.addUserUrl);
        
        // 发请求，获取结果
        Map<String, Object> resultMap = getResult(addUsercase);
        String result = resultMap.get("result").toString();
        // 验证返回结果
        addUsercase.setAge((Integer) resultMap.get("age"));
        addUsercase.setName(resultMap.get("name").toString());
        addUsercase.setPassword(resultMap.get("password").toString());
        addUsercase.setSex(resultMap.get("sex").toString());
        addUsercase.setPermission((Integer) resultMap.get("permission"));
        addUsercase.setIsDelete((Integer) resultMap.get("isDelete"));
        Assert.assertEquals(addUsercase.getExpected(), result);
    }

    private Map<String, Object> getResult(AddUserCase addUsercase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        String nameValue = addUsercase.getName()+new Random().nextInt(1000);
        map.put("name", nameValue);
        param.put("name", nameValue);
        param.put("password", addUsercase.getPassword());
        map.put("password", addUsercase.getPassword());
        int ageValue = addUsercase.getAge()+new Random().nextInt(100);
        param.put("age", ageValue);
        map.put("age", ageValue);
        param.put("sex", addUsercase.getSex());
        map.put("sex", addUsercase.getSex());
        param.put("permission", addUsercase.getPermission());
        map.put("permission", addUsercase.getPermission());
        param.put("isDelete", addUsercase.getIsDelete());
        map.put("isDelete", addUsercase.getIsDelete());
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        
        // 设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        map.put("result", result);
        return map;
    }
}
