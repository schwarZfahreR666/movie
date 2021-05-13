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
import com.f.movie.utils.Md5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class JbccController {

    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    final String key = "0a3d45ba-6e25-418f-bcbb-2413a07110ff";

    @GetMapping("/addUser")
    public State addUser(@RequestParam String id, @RequestParam String username, @RequestParam String password, @RequestParam String nickname, @RequestParam String email, @RequestParam String phone, @RequestParam String hobbies,@RequestParam String token){

        Map<String,String> params = new HashMap<String,String>();
        params.put("id",id);
        params.put("username",username);
        params.put("password",password);
        params.put("nickname",nickname);
        params.put("email",email);
        params.put("phone",phone);
        params.put("hobbies",hobbies);
        String serverToken = Md5.tokenGenerator(params,key);
        if(!serverToken.equals(token)){
            return new State(400,"无访问权限");
        }


        User user = new User(id,username,password,nickname,email,phone,hobbies,"正常");
        User result = userService.addUser(user);
        User result2 = userService.insertUser(user);
        if(result==null){
            return new State(400,"信息插入失败");
        }else if(result2==null){
            return new State(401,"已存在该用户id，更新数据请忽略此错误");
        } else{
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
    public State addComment(@RequestParam String commentId, @RequestParam String movieId, @RequestParam String userId, @RequestParam String content,@RequestParam String time){
        Comment comment = new Comment(commentId,movieId,userId,content,time);
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

    @GetMapping("/deluser")
    public State delUser(@RequestParam String userId){
        User user = userService.queryUser(userId);
        if(user.getState().equals("正常")) {
            user.setState("已注销");
            user = userService.addUser(user);
            }

        if(user==null){
            return new State(400,"用户删除失败");
        }else{
            return new State(200,"用户删除成功");
        }

    }
}
