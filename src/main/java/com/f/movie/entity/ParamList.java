package com.f.movie.entity;

import lombok.Data;

import java.util.List;
@Data
public class ParamList {
    private String userId;
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

    public ParamList(String userId, String top1, String top2, String top3, String top4, String top5, String top6, String top7, String top8, String top9, String top10) {
        this.userId = userId;
        this.top1 = top1;
        this.top2 = top2;
        this.top3 = top3;
        this.top4 = top4;
        this.top5 = top5;
        this.top6 = top6;
        this.top7 = top7;
        this.top8 = top8;
        this.top9 = top9;
        this.top10 = top10;
    }
}
