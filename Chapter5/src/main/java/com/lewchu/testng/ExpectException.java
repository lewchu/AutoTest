package com.lewchu.testng;

import org.testng.annotations.Test;

/**
 * 异常测试
 */
public class ExpectException {

    /** 
     * 什么时候会用到异常测试？
     * 当我们期望结果为某一个异常时
     * 比如：传入了某些不合法的参数，程序会抛出异常，我们期望结果为这个异常
     */
    
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }
    
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }

}
