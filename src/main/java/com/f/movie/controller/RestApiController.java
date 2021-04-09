package com.f.movie.controller;
import com.f.movie.pojo.User;
import com.f.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("/test")
    public User getById(@RequestParam("id") int id){

        User res = userService.getAcctById(id);

        return res;

    }

    @ResponseBody
    @PostMapping("/test")
    public User insert2(User user){

        return userService.insert(user);

    }

}
