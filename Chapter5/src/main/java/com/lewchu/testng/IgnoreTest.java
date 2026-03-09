package com.lewchu.testng;

import org.testng.annotations.Test;

/**
 * 忽略测试
 */
public class IgnoreTest {
    
    @Test
    public void ignoreTest  (){
        System.out.println("ignoreTest1执行");
    }
    
    @Test(enabled = false)
    public void ignoreTest2  (){
        System.out.println("ignoreTest2执行");
    }
}
