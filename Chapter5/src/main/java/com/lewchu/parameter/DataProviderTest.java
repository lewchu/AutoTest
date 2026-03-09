package com.lewchu.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    
    @Test(dataProvider = "data", enabled = false)
    public void testDataProvider(String name, int age) {
        System.out.println("name:" + name + "; age:" + age);
    }
    
    @DataProvider
    public Object[][] data() {
        Object[][] data = new Object[][]{
                {"张三", 18},
                {"李四", 19},
                {"王五", 20}
        };
        return data;
    }
    
    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("name:" + name + "; age:" + age);
    }
    
    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("name:" + name + "; age:" + age);
    }
    
    @DataProvider(name = "methodData")
    public Object[][] methodData(Method  method) {
        Object[][] objects = null;
        if (method.getName().equals("test1")) {
            objects = new Object[][]{
                    {"张三", 18},
                    {"李四", 19},
                    {"王五", 20}
            };
        } else if (method.getName().equals("test2")) {
            objects = new Object[][]{
                    {"赵六", 18},
                    {"孙七", 19},
                    {"周八", 20}
            };
        }
        return objects;
    }
    
}
