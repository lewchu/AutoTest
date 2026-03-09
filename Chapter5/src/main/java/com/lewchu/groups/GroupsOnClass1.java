package com.lewchu.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("GroupsOnClass111111 stu1运行");
    }

    public void stu2(){
        System.out.println("GroupsOnClass111111 stu2运行");
    }
}
