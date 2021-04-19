package com.f.movie.entity;

import lombok.Data;

@Data
public class Movie {
    private String id;
    private String name;
    private String english_name;
    private String directors;
    private String writer;
    private String actors;
    private String date;
    private String style1;
    private String style2;
    private String style3;
    private String country;
    private String rate;
    private String dataID;
    private String language;
    private String duration;
    private String introduction;
    private String pic_url;
    private String url;

    public Movie(String id, String name, String english_name, String directors, String writer, String actors, String date, String style1, String style2, String style3, String country, String rate, String dataID, String language, String duration, String introduction, String pic_url, String url) {
        this.id = id;
        this.name = name;
        this.english_name = english_name;
        this.directors = directors;
        this.writer = writer;
        this.actors = actors;
        this.date = date;
        this.style1 = style1;
        this.style2 = style2;
        this.style3 = style3;
        this.country = country;
        this.rate = rate;
        this.dataID = dataID;
        this.language = language;
        this.duration = duration;
        this.introduction = introduction;
        this.pic_url = pic_url;
        this.url = url;
    }

    public Movie() {
    }

    public Movie(String id) {
        this.id = id;
    }
}
