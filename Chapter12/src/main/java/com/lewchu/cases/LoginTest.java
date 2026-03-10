package com.lewchu.cases;

import com.alibaba.fastjson2.JSONObject;
import com.lewchu.config.TestConfig;
import com.lewchu.enums.InterFaceName;
import com.lewchu.model.LoginCase;
import com.lewchu.utils.ConfigFileUtil;
import com.lewchu.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    
    @BeforeTest(groups = "loginTrue", description = "测试准备工作，获取HttpClient对象")
    public void beforeTest() {
        TestConfig.getUserInfoUrl = ConfigFileUtil.getUrl(InterFaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFileUtil.getUrl(InterFaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFileUtil.getUrl(InterFaceName.ADDUSER);
        TestConfig.updateUserInfoUrl = ConfigFileUtil.getUrl(InterFaceName.UPDATEUSERINFO);
        TestConfig.loginUrl = ConfigFileUtil.getUrl(InterFaceName.LOGIN);
        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue", description = "用户登陆成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        
        // 第一步发送请求，获取结果
        String result = getResult(loginCase);
        // 验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    @Test(groups = "loginFalse", description = "用户登陆失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        // 第一步发送请求，获取结果
        String result = getResult(loginCase);
        // 验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("name", loginCase.getName());
        param.put("password", loginCase.getPassword());
        post.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        // 添加cookie
        TestConfig.cookieStore = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }
    
//    @Test(groups = "loginTrue", description = "用户登陆接口")
//    public void login() {
//        System.out.println("登陆成功");
//        // 设置cookie
//        Cookie cookie = new BasicClientCookie("token", "11112223333");
//        CookieStore cookieStore = new BasicCookieStore();
//        cookieStore.addCookie(cookie);
//        TestConfig.cookieStore = cookieStore;
//    }
}
