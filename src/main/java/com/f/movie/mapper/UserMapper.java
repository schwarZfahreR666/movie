package com.f.movie.mapper;

import com.f.movie.entity.IdMap;
import com.f.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int insertUser(User user);
    public User getUser(String username);
    public int delUser(String id);

}
