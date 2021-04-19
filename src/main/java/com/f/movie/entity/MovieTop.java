package com.f.movie.entity;

import lombok.Data;

import java.util.List;

@Data
public class MovieTop {
    private String user_id;
    private String top1;
    private String top2;
    private String top3;
    private String top4;
    private String top5;
    private String top6;
    private String top7;
    private String top8;
    private String top9;
    private String top10;

    public MovieTop() {
    }

    public MovieTop(String user_id) {
        this.user_id = user_id;
    }
}
