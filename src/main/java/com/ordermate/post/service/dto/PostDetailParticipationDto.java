package com.ordermate.post.service.dto;

import com.ordermate.member.domain.Member;
import com.ordermate.participant.domain.Role;
import lombok.Getter;

@Getter
public class PostDetailParticipationDto {
    private String username;
    private String name;
    private Role role;

    public PostDetailParticipationDto(Role role, Member member, Boolean isAnonymous) {
        this.username = member.getUsername();
        this.name = isAnonymous ? member.getNickname() : member.getName();
        this.role = role;
    }
}


