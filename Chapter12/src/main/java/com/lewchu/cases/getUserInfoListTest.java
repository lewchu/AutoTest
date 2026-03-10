package com.lewchu.cases;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.lewchu.config.TestConfig;
import com.lewchu.model.GetUserListCase;
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
import java.util.List;

public class getUserInfoListTest {
    
    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户列表")
    public void getUserInfoList() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        List<GetUserListCase> getUserListCase = sqlSession.selectList("getUserInfoListCase", "女");
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        
        // 发送请求获取结果
        JSONArray result = getJsonResult();
        List<User> uList = result.toJavaList(User.class);
        // 验证结果
        List<User> userList = sqlSession.selectList("getUserList", "女");
        for (User user : userList) {
            System.out.println("获取的用户列表：" + user.toString());
        }
        JSONArray jsonArray = new JSONArray(userList);
        List<User> expectList = jsonArray.toJavaList(User.class);
        Assert.assertEquals(jsonArray.size(), userList.size());
        for (int i = 0; i < expectList.size(); i++) {
            User expect = expectList.get(i);
            User actual = uList.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());
        }
    }

    private JSONArray getJsonResult() throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("sex", "女");
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        return JSONArray.parseArray(result);
    }
}
