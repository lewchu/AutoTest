package com.lewchu.cookies;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    
    private String url;
    private ResourceBundle bundle;

    //用来存储 cookies信息
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        HttpGet get = new HttpGet(this.url + bundle.getString("getCookies.url"));
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        // 获取cookies信息
        this.store =client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name + "; cookie value = " + value);
        }
    }
    
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostWithCookies() throws IOException {
        String uri = this.url + bundle.getString("postWithCookies.url");
        // 声明一个client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        // 声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(uri);
        // 添加参数
        JSONObject param = new JSONObject();
        param.put("name", "husan");
        param.put("age", 20);
        // 将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("content-type", "application/json");
        //设置cookies信息
        client.setCookieStore(this.store);
        // 执行post方法
        HttpResponse response =client.execute(post);
        // 获取响应结果
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println("结果：" + jsonObject);
        Assert.assertEquals(jsonObject.getInteger("code"), 0);
        Assert.assertEquals(jsonObject.getString("name"), "husan");
    }
    
}
