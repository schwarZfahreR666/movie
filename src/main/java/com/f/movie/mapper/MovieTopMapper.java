package com.f.movie.mapper;

import com.f.movie.entity.MovieTop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieTopMapper {
    public MovieTop getById(String id);
    public int insertMovieTop(MovieTop movieTop);
}
