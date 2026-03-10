package com.lewchu.model;

import lombok.Data;

@Data
public class LoginCase {
    private int id;
    private String name;
    private String password;
    private String expected;
}
