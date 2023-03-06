package com.ordermate.comment.service.dto;


import com.ordermate.comment.domain.Comment;
import com.ordermate.member.domain.Member;
import com.ordermate.post.domain.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentAddDto(
        LocalDateTime createdAt,
        String content
){
    public Comment toEntity(Member member, Post post) {
        return Comment.builder().member(member)
                .post(post).content(content)
                .createdAt(createdAt).build();
    }
}
