package com.lewchu.model;

import lombok.Data;

@Data
public class AddUserCase {
    private int id;
    private String name;
    private String password;
    private int age;
    private String sex;
    private int permission;
    private int isDelete;
    private String expected;
}
