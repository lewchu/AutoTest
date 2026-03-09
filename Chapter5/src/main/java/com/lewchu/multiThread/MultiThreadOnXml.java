package com.lewchu.multiThread;

import org.testng.annotations.Test;

/**
 * 通过xml实现多线程测试
 */
public class MultiThreadOnXml {
    
    @Test
    public void test1(){
        System.out.printf("Thread ID: %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("Thread ID: %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("Thread ID: %s%n", Thread.currentThread().getId());
    }
}
