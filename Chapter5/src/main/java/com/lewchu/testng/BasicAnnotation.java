package com.lewchu.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BasicAnnotation {
    
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }
    
    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }
    
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod这是在测试用例执行之前运行的方法");
    }
    
    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod这是在测试用例执行之后运行的方法");
    }
    
    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass这是在类运行之前运行的方法");
    }
    
    @AfterClass
    public void afterClass(){
        System.out.println("afterClass这是在类运行之后运行的方法");
    }
    
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite测试套件");
    }
    
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite测试套件");
    }
}
