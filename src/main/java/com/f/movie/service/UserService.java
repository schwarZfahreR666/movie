package com.f.movie.service;
import com.f.movie.pojo.User;
import com.f.movie.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getAcctById(int id){
        return userMapper.getById(id);
    }

    public User insert(User user){
        userMapper.insert(user);
        return user;
    }

}
