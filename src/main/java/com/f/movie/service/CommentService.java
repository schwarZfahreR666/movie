package com.f.movie.service;


import cn.tdchain.Trans;
import cn.tdchain.TransHead;
import cn.tdchain.jbcc.Connection;
import cn.tdchain.jbcc.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.f.movie.entity.Comment;
import com.f.movie.entity.IdMap;
import com.f.movie.mapper.MapMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CommentService {

    @Autowired
    BaseService baseService;
    @Autowired
    MapMapper mapMapper;


    /*
     * 插入电影评论
     * */
    @CachePut(cacheNames="comment",key="#comment.commentId")
    public Comment addComment(Comment comment){
        //# 构建出一笔交易信息
        Trans trans = trans(comment.getCommentId(),comment.getMovieId(),comment.getUserId(),comment.getContent(),comment.getTime());
        //# 发起一笔交易到云区块链服务中
        Result<TransHead> result = baseService.getConnection().addTrans(trans);

        if (result.isSuccess()) {
            //# 根据返回结果状态判断是否成功
            baseService.getLog().info("\n===> add trans success.");
            return comment;
        } else {
            baseService.getLog().info("\n===> add trans fail.");
            return new Comment("400");
        }

//        Tools.printResult(result);

    }


    private Trans trans(String commentId,String movieId,String userId,String content,String time) {
        Trans trans = new Trans();
        trans.setKey(commentId);//# key是当前交易的维度
        Map<String, Object> data = new HashMap<>();
        data.put("movieId", movieId);
        data.put("userId", userId);
        data.put("content", content);
        data.put("time", time);

        trans.setData(JSON.toJSONString(data));
        trans.setType("Test");
        trans.setTimestamp(new Date().getTime());

        return trans;
    }

    /*
     * 查询电影评论*/

    @Cacheable(cacheNames = "comment",key="#key")
    public Comment queryComment(String key){
        int startIndex = 0;
        int endIndex = 10;
        Comment comment = new Comment();
        System.out.println("query comment info");
        try {
            Result<List<Trans>> result = baseService.getConnection().getTransHistoryByKey(key, startIndex, endIndex);

            String data = result.getEntity().get(0).getData();
            String commentId = result.getEntity().get(0).getKey();
            JSONObject jsonObject =  JSONObject.parseObject(data);
            String movieId = jsonObject.get("movieId").toString();
            String userId = jsonObject.get("userId").toString();
            String content = jsonObject.get("content").toString();
            String time = jsonObject.get("time").toString();

            comment.setCommentId(commentId);
            comment.setMovieId(movieId);
            comment.setUserId(userId);
            comment.setContent(content);
            comment.setTime(time);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    public List<IdMap> getMapByComment(String id){
        return mapMapper.getByComment(id);
    }

}
