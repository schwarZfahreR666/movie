package com.f.movie.controller;


import com.f.movie.entity.Comment;
import com.f.movie.entity.State;
import com.f.movie.entity.User;
import com.f.movie.service.CommentService;
import com.f.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class JbccController {

    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @GetMapping("/addUser")
    public State addUser(@RequestParam String id, @RequestParam String username, @RequestParam String password, @RequestParam String nickname, @RequestParam String email, @RequestParam String phone, @RequestParam String hobbies){
        User user = new User(id,username,password,nickname,email,phone,hobbies);
        User result = userService.addUser(user);
        if(result==null){
            return new State(400,"信息插入失败");
        }else{
            return new State(200,"信息插入成功");
        }
    }

    @GetMapping("/queryUser")
    public User queryUser(@RequestParam String key){

        User result = userService.queryUser(key);
        if(result == null){
            return new User("400");
        }else{
            return result;
        }

    }


    @GetMapping("/addComment")
    public State addComment(@RequestParam String commentId, @RequestParam String movieId, @RequestParam String userId, @RequestParam String content){
        Comment comment = new Comment(commentId,movieId,userId,content);
        Comment result = commentService.addComment(comment);
        if(result == null){
            return new State(400,"信息插入失败");
        }else{
            return new State(200,"信息插入成功");
        }
    }

    @GetMapping("/queryComment")
    public Comment queryComment(@RequestParam String key){
        Comment result = commentService.queryComment(key);
        if(result == null){
            return new Comment("400");
        }else{
            return result;
        }

    }
}
