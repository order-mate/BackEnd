package com.ordermate.member.controller.dto;

import com.ordermate.member.domain.GenderType;
import com.ordermate.member.service.dto.JoinMemberDto;

public record JoinRequestDto(
        String username,
        String password,
        String name,
        String nickname,
        GenderType gender,
        String school,
        String major

) {

    public JoinMemberDto toServiceDto() {
        return new JoinMemberDto(username, password, name, nickname, gender, school, major);
    }
}
