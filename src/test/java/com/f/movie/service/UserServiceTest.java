package com.f.movie.service;

import com.f.movie.entity.IdMap;
import com.f.movie.entity.MovieTop;
import com.f.movie.entity.User;
import org.assertj.core.util.Lists;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
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
        //System.out.println("测试addUser");
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
        String uuid = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        assertEquals(userService.queryUser(uuid).getId(), uuid);
        uuid = "0980-4663-a94c-e7f9d298cf70";
        assertNull(userService.queryUser(uuid));
    }

    @Test
    void getMapByUser() {
        String uuid = "0bbc537a-a5c8-11eb-8e86-9801a7c99217";
        List<IdMap> maps = userService.getMapByUser(uuid);
        for(IdMap map : maps){
            System.out.println(map.toString());
        }
        assertTrue(userService.getMapByUser("dfdafdsf").isEmpty());
    }

    @Test
    void insertIdMap() {
        String commentId = "3999d156-a7fc-11eb-9336-39184f9e874f";
        String uuid = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        String movieId = "1086";
        IdMap idMap = new IdMap(commentId, movieId,uuid);
        assertEquals(userService.insertIdMap(uuid,commentId,movieId),idMap);
        assertNull(userService.insertIdMap(uuid,commentId,movieId));
    }

    @Test
    void getMovieTop() {
        String uuid = "0bbc537a-a5c8-11eb-8e86-9801a7c99217";
        assertEquals(userService.getMovieTop(uuid).getUser_id(), uuid);
        assertNull(userService.getMovieTop("dfdfds"));
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
        //System.out.println("测试insertUser");
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
        assertNull(userService.insertUser(user));
    }

    @Test
    void getUser() {
        String username = "ws123";
        assertEquals(userService.getUser(username).getUsername(), username);
        assertNull(userService.getUser("fdsfds"));

        String nickname = "zhangruisheng";
        assertEquals(userService.getUser("nickname", nickname).getId(), "36e8a1c6-b16d-11eb-9e8c-9801a7c99217");
        username = "zhangruisheng";
        assertEquals(userService.getUser("username", username).getId(), "36e8a1c6-b16d-11eb-9e8c-9801a7c99217");
        assertNull(userService.getUser("key", "key"));

    }

    @Test
    void delUser() {
        //System.out.println("测试delUser");
        String uuid = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        assertEquals(userService.delUser(uuid).getId(), uuid);
        uuid = "sdfdfdfdsfs";
        assertNull(userService.delUser(uuid));

    }

    @Test
    void getUsers() {
        Pair<Integer,List<User>> result = userService.getUsers(0,10);
        System.out.println("用户总数: " + result.getValue0());
        for(User user: result.getValue1()){
            System.out.println(user.toString());
        }

        result = userService.getUsers(5,10);
        System.out.println("用户总数: " + result.getValue0());
        for(User user: result.getValue1()){
            System.out.println(user.toString());
        }
    }

}