package com.f.movie.service;

import com.f.movie.entity.IdMap;
import com.f.movie.entity.Movie;
import com.f.movie.entity.MovieVector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Test
    void getMovieById(){
        String movieId = "1000";
        assertEquals(movieService.getMovieById(movieId).getId(), movieId);
        movieId = "50000";
        assertNull(movieService.getMovieById(movieId));
    }

    @Test
    void getMovieVector(){
        String movieId = "1000";
        assertEquals(movieService.getMovieVector(movieId).getMovieId(), movieId);
        movieId = "50000";
        assertNull(movieService.getMovieVector(movieId));
    }

    @Test
    void getMovies(){
        int start = 100;
        int num = 10;
        List<Movie> movies = movieService.getMovies(start,num);
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }

    }

    @Test
    void searchMovies(){
        String name = "利刃出鞘";
        List<Movie> movies = movieService.searchMovies(name);
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        name = "12222";
        movies = movieService.searchMovies(name);
        for (Movie movie : movies) {
            System.out.println(movie);
        }

    }

    @Test
    void getMapByMovie(){
        String movieId = "1093";

        List<IdMap> maps = movieService.getMapByMovie(movieId);
        for(IdMap map : maps){
            System.out.println(map.toString());
        }
        assertTrue(movieService.getMapByMovie("dfdafdsf").isEmpty());


    }


    @Test
    void getMovieNum(){
        assertEquals(movieService.getMovieNum(),4402);
    }

}
