package com.lewchu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {
    
    public static SqlSession getSqlSession() throws IOException {
        // 获取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        SqlSessionFactory session = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = session.openSession();
        return sqlSession;
    }
}
