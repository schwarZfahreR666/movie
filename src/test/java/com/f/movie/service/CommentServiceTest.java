package com.f.movie.service;


import com.f.movie.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void addComment(){
        String commentId = "comment1111";
        String movieId = "3201";
        String userId = "3a9a4e7b-0980-4663-a94c-e7f9d298cf70";
        String time = "2021-05-10";
        String content = "good";
        Comment comment = new Comment(commentId,movieId,userId,time,content);

        assertEquals(commentService.addComment(comment).getCommentId(),commentId);
    }

    @Test
    void queryComment(){
        String commentId = "comment1111";
        assertEquals(commentService.queryComment(commentId).getCommentId(),commentId);

        commentId = "comment1111222";
        assertNull(commentService.queryComment(commentId).getCommentId());
    }
}
