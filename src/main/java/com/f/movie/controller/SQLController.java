package com.f.movie.controller;

import com.f.movie.entity.*;
import com.f.movie.mapper.MovieVectorMapper;
import com.f.movie.service.CommentService;
import com.f.movie.service.MovieService;
import com.f.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getVector")
    public MovieVector getVector(@RequestParam String movieId){
        MovieVector result = movieService.getMovieVector(movieId);
        if(result == null){
            return new MovieVector("400");
        }
        return result;
    }

    @GetMapping("/getUsername")
    public User getUsername(@RequestParam String username){
        User result = userService.getUser(username);
        if(result == null){
            return new User("400");
        }else{
            return result;
        }
    }

    @PostMapping("/insertMovieTop")
    public State insertMovieTop(@RequestBody ParamList params){
        String userId = params.getUserId();
        List<String> tops = new ArrayList<String>();
        tops.add(0, params.getTop1());
        tops.add(1, params.getTop2());
        tops.add(2, params.getTop3());
        tops.add(3, params.getTop4());
        tops.add(4, params.getTop5());
        tops.add(5, params.getTop6());
        tops.add(6, params.getTop7());
        tops.add(7, params.getTop8());
        tops.add(8, params.getTop9());
        tops.add(9, params.getTop10());
        MovieTop result = userService.insertMovieTop(userId,tops);
        if(result == null){
            return new State(400,"插入信息失败");
        }else{
            return new State(200,"插入信息成功");
        }
    }


}
