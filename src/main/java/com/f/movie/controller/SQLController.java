package com.f.movie.controller;

import com.f.movie.entity.IdMap;
import com.f.movie.entity.Movie;
import com.f.movie.entity.MovieTop;
import com.f.movie.entity.State;
import com.f.movie.service.CommentService;
import com.f.movie.service.MovieService;
import com.f.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SQLController {
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    CommentService commentService;

    @GetMapping("/getMapByUser")
    public List<IdMap> getMapByUser(@RequestParam String userId){
        List<IdMap> result = userService.getMapByUser(userId);

        if(result.isEmpty()){
            List<IdMap> error = new ArrayList<>();
            error.add(new IdMap("400"));
            return error;
        }
        return result;
    }

    @GetMapping("/insertIdMap")
    public State insertIdMap(@RequestParam String userId,@RequestParam String commentId,@RequestParam String movieId){
        IdMap result = userService.insertIdMap(userId,commentId,movieId);
        if(result == null){
            return new State(400,"信息插入失败");
        }else{
            return new State(200,"信息插入成功");
        }
    }
    @GetMapping("/getMovieTop")
    public MovieTop getMovieTop(@RequestParam String userId){
        MovieTop result = userService.getMovieTop(userId);
        if(result == null){
            return new MovieTop("400");
        }else{
            return result;
        }

    }


    @GetMapping("/getMovieById")
    public Movie getMovieById(@RequestParam String movieId){
        Movie result = movieService.getMovieById(movieId);
        if(result == null){
            return new Movie("400");
        }else{
            return result;
        }
    }

    @GetMapping("/getMovies")
    public List<Movie> getMovies(@RequestParam int start,@RequestParam int count){
        List<Movie> result = movieService.getMovies(start,count);
        if(result.isEmpty()){
            List<Movie> error = new ArrayList<>();
            error.add(new Movie("400"));
            return error;
        }else{
            return result;
        }
    }

    @GetMapping("/getMapByMovie")
    public List<IdMap> getMapByMovie(@RequestParam String movieId){
        List<IdMap> result = movieService.getMapByMovie(movieId);

        if(result.isEmpty()){
            List<IdMap> error = new ArrayList<>();
            error.add(new IdMap("400"));
            return error;
        }
        return result;
    }

    @GetMapping("/getMapByComment")
    public List<IdMap> getMapByComment(@RequestParam String commentId){
        List<IdMap> result = commentService.getMapByComment(commentId);
        if(result.isEmpty()){
            List<IdMap> error = new ArrayList<>();
            error.add(new IdMap("400"));
            return error;
        }
        return result;
    }
}
