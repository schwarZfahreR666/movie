package com.f.movie.entity;

import lombok.Data;

@Data
public class State {
    private int code;
    private String info;

    public State(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public State() {
    }
}
