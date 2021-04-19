package com.f.movie.mapper;

import com.f.movie.entity.IdMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {
    public List<IdMap> getByComment(String commentId);
    public List<IdMap> getByMovie(String movieId);
    public List<IdMap> getByUser(String userId);
    public int insertMap(IdMap idmap);
}
