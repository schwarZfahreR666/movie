package com.f.movie.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class MovieVector {
    private String movieId;
    private String movieVector;

    public MovieVector() {
    }

    public MovieVector(String movieId, String movieVector) {
        this.movieId = movieId;
        this.movieVector = movieVector;
    }

    public MovieVector(String movieId) {
        this.movieId = movieId;
    }
}
