package com.f.movie.entity;

import lombok.Data;

@Data
public class Comment {
    private String commentId;
    private String movieId;
    private String userId;
    private String content;

    public Comment() {
    }

    public Comment(String commentId, String movieId, String userId, String content) {
        this.commentId = commentId;
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
    }

    public Comment(String commentId) {
        this.commentId = commentId;
    }
}
