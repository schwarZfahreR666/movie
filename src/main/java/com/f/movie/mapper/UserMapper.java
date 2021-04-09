package com.f.movie.mapper;


import com.f.movie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id,username,password from test where id=#{id}")
    public User getById(int id);

    @Select("insert into test(`id`,`username`,`password`) values(#{id},#{username},#{password})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(User user);

    public void insert2(User user);
}
