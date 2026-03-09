package com.lewchu.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    /**
     * 参数化测试
     * 通过xml文件参数化
     */
    @Test
    @Parameters({"name", "age"})
    public void parameterTest1(String name, int age) {
        System.out.println("name:" + name + " age:" + age);
    }
}
