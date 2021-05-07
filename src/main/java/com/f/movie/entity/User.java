package com.f.movie.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String hobbies;
    private String state;  //作为是否已经删除的标记， 已注销、正常

    public User(String id, String username, String password, String nickname, String email, String phone, String hobbies, String state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.hobbies = hobbies;
        this.state = state;
    }

    public User() {
    }

    public User(String id) {
        this.id = id;
    }
    public User(String id,String username) {
        this.id = id;
        this.username = username;
    }
}
