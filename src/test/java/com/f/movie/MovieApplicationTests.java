package com.f.movie;

import com.f.movie.entity.Movie;
import com.f.movie.entity.MovieVector;
import com.f.movie.entity.User;
import com.f.movie.service.BaseService;
import com.f.movie.service.CommentService;
import com.f.movie.service.MovieService;
import com.f.movie.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MovieApplicationTests {

    @Autowired
    BaseService baseService;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    CommentService commentService;
    @Test
    void getMovies() {

        for(Movie movie:movieService.getMovies(1,20)){
            System.out.println(movie);
        }

    }

    @Test
    void getMap(){
        System.out.println(movieService.getMapByMovie("120"));
        System.out.println(commentService.getMapByComment("10"));
        System.out.println(userService.getMapByUser("user3203"));
    }

    @Test
    void insertIdMap(){
        System.out.println(userService.insertIdMap("user3210","101","130"));
    }


    @Test
    void insertMovieTop(){
        List<String> topList = new ArrayList<String>();
        for (int i = 0; i < 10 ; i++) {
            topList.add("12"+i);
        }
        System.out.println(userService.insertMovieTop("user3203",topList));

    }
    @Test
    void getMovieTop(){

        System.out.println(userService.getMovieTop("user32010"));

    }
    @Test
    void getPath(){
        String Path = getClass().getClassLoader().getResource("0x043086bb88a993387dcdebea6da18e98.pfx").getPath();
        System.out.println(Path);
    }
    @Test
    void getVector(){
        MovieVector movieVector = movieService.getMovieVector("10");
        System.out.println(movieVector);
    }
    @Test
    void testUser(){
        userService.insertUser(new User("user321","zr"));
        System.out.println(userService.getUser("zr"));
    }
    @Test
    void num(){
        System.out.println(movieService.getMovieNum());
    }

}
