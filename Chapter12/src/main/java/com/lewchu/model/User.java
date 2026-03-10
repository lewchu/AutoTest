package com.lewchu.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class User {
    @JSONField(ordinal = 1)
    private int id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String password;
    @JSONField(ordinal = 4)
    private int age;
    @JSONField(ordinal = 5)
    private String sex;
    @JSONField(ordinal = 6)
    private int permission;
    @JSONField(ordinal = 7)
    private int isDelete;
    
    @Override
    public String toString() {
        return "{id=" + this.id + ", name=" + this.name + ", password=" + this.password + ", age=" + this.age + ", sex=" + this.sex + ", permission=" + this.permission + ", isDelete=" + this.isDelete + "}";
    }
}
