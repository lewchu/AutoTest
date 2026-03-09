package com.lewchu.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    
    public void stu1(){
        System.out.println("GroupsOnClass222222 stu1运行");
    }
    
    public void stu2(){
        System.out.println("GroupsOnClass222222 stu2运行");
    }
}
