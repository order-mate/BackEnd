package com.ordermate.post.service.dto;

import com.ordermate.member.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailCommentDto {
    private String ownerName;
    private String content;
    private LocalDateTime createdAt;

    public PostDetailCommentDto(String content, LocalDateTime createdAt, Member owner, Boolean isAnonymous) {
        this.ownerName =  isAnonymous ? owner.getNickname() : owner.getName();
        this.content = content;
        this.createdAt = createdAt;
    }
}
