package com.f.movie.service;

import com.f.movie.entity.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void addUser() {
        System.out.println("测试addUser");
        //String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String uuid = UUID.randomUUID().toString();
        User user = new User(uuid,
                "ws1234",
                "12345",
                "ws1234",
                "rzxyws2010@163.com",
                "18906338948",
                "动作",
                "正常");
        assertEquals(userService.addUser(user).getId(),user.getId());
    }

    @Test
    void queryUser() {
    }

    @Test
    void getMapByUser() {
    }

    @Test
    void insertIdMap() {
    }

    @Test
    void getMovieTop() {
    }

    @Test
    void insertMovieTop() {
        String uuid = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        List<String> top10 = Lists.newArrayList(
                "424",
                "1200",
                "2017",
                "2669",
                "383",
                "31",
                "189",
                "2596",
                "1518",
                "1906");
        assertEquals(userService.insertMovieTop(uuid, top10).getUser_id(), uuid);
        //assertEquals(userService.insertMovieTop(uuid, top10), null);
    }

    @Test
    void insertUser() {
        System.out.println("测试insertUser");
        String uuid = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        User user = new User(uuid,
                "ws1234",
                "12345",
                "ws1234",
                "rzxyws2010@163.com",
                "18906338948",
                "动作",
                "正常");
        assertEquals(userService.insertUser(user).getId(), uuid);
        assertEquals(userService.insertUser(user), null);
    }

    @Test
    void getUser() {

    }

    @Test
    void delUser() {
        System.out.println("测试delUser");
        String uuid = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        assertEquals(userService.delUser(uuid).getId(), uuid);
        uuid = "sdfdfdfdsfs";
        assertEquals(userService.delUser(uuid), null);

    }

    @Test
    void getUsers() {
    }

}