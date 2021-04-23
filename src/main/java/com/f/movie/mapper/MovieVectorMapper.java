package com.f.movie.mapper;

import com.f.movie.entity.MovieVector;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieVectorMapper {

    public MovieVector getMovieVector(String movieId);
}
