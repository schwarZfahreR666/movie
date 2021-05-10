package com.f.movie.service;

import com.f.movie.entity.IdMap;
import com.f.movie.entity.Movie;
import com.f.movie.entity.MovieVector;
import com.f.movie.mapper.MapMapper;
import com.f.movie.mapper.MovieMapper;
import com.f.movie.mapper.MovieVectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    MapMapper mapMapper;
    @Autowired
    MovieVectorMapper movieVectorMapper;

    public Movie getMovieById(String id){
        return movieMapper.getById(id);
    }

    public MovieVector getMovieVector(String id){ return movieVectorMapper.getMovieVector(id);}

    public List<Movie> getMovies(int start,int count){
        return movieMapper.getMovies(start,count);
    }

    public List<Movie> searchMovies(String name){
        return movieMapper.searchMovies(name);
    }
    public List<IdMap> getMapByMovie(String id){
        return mapMapper.getByMovie(id);
    }

    public int getMovieNum(){ return movieMapper.getMovieNum(); }

    public int delMovie(String id){ return movieMapper.delMovie(id); }

    public int insertMovie(Movie movie){ return movieMapper.insertMovie(movie); }

    public int updateMovie(Movie movie){
        return movieMapper.updateMovie(movie);
    }
}
