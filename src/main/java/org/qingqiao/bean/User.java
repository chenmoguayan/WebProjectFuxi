package org.qingqiao.bean;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String birthday;
    private String address;
    private String hobby;

    public User(Integer id, String username, String password, String sex, String birthday, String address, String hobby) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.hobby = hobby;
    }

    public User() {
    }
}
