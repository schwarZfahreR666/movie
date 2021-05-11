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

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof IdMap)){
            return false;
        }
        IdMap idMap = (IdMap)obj;
        return getCommentId().equals(idMap.getCommentId())&&
                getMovieId().equals(idMap.getMovieId())&&
                getUserId().equals(idMap.getUserId());
    }

}
