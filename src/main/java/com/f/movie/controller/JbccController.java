package com.f.movie.controller;


import com.f.movie.pojo.User;
import com.f.movie.service.JbccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JbccController {

    @Autowired
    JbccService jbccService;

    @GetMapping("/testJbcc")
    public void testJbcc(@RequestParam String transKey,@RequestParam String username,@RequestParam String password){
        System.out.println("test jbcc");
        User user = new User(transKey,username,password);
        jbccService.addTrans(user);
    }

    @GetMapping("/jbccBykey")
    public User queryByKey(@RequestParam String key){
        return jbccService.queryByKey(key);

    }
}
