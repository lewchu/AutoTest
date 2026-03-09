package com.lewchu.multiThread;

import org.testng.annotations.Test;

/**
 * 通过注解实现多线程测试
 */
public class MultiThreadAnnotationTest {
    
    @Test(threadPoolSize = 3, invocationCount = 10)
    public void test1() {
        System.out.printf("Thread ID: %s%n", Thread.currentThread().getId());
    }
}
