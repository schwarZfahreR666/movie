package com.f.movie.entity;

import lombok.Data;

@Data
public class IdMap {
    private String commentId;
    private String movieId;
    private String userId;

    public IdMap() {
    }

    public IdMap(String commentId, String movieId, String userId) {
        this.commentId = commentId;
        this.movieId = movieId;
        this.userId = userId;
    }

    public IdMap(String commentId) {
        this.commentId = commentId;
    }
}
