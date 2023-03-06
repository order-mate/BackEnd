package com.ordermate.comment.service.dto;


import com.ordermate.comment.domain.Comment;
import com.ordermate.member.domain.Member;
import com.ordermate.post.domain.Post;
import lombok.Builder;

import java.time.LocalDateTime;

public class CommentDto{
    private Post post;
    private Member member;
    private LocalDateTime createdAt;
    private String content;

    public CommentDto(Comment comment) {
        post = comment.getPost();
        member = comment.getMember();
        createdAt = comment.getCreatedAt();
        content = comment.getContent();
    }
}
