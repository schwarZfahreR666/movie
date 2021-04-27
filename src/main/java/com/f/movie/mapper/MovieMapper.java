package com.f.movie.mapper;


import com.f.movie.entity.Movie;
import com.f.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {
//  通过id获取单独电影信息
    public Movie getById(String id);
//  批量获取电影，start是开始索引，count为数量
    public List<Movie> getMovies(int start,int count);
    // 获取电影数量
    public int getMovieNum();
    // 删除电影
    public int delMovie(String id);
    //插入电影
    public int insertMovie(Movie movie);
    //更新电影
    public int updateMovie(Movie movie);

}
