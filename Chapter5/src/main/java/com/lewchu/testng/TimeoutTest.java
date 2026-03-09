package com.lewchu.testng;

import org.testng.annotations.Test;

public class TimeoutTest {
    
    @Test(timeOut = 3000)
    public void timeoutTestSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }
    
    @Test(timeOut = 3000)
    public void timeoutTestFailed() throws InterruptedException {
        Thread.sleep(4000);
    }
}
