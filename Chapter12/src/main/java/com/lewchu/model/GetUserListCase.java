package com.lewchu.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String expected;
}
