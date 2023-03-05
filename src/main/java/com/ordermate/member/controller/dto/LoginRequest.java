package com.ordermate.member.controller.dto;

import com.ordermate.member.service.dto.LoginMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

    public LoginMemberDto toServiceDto() {
        return new LoginMemberDto(username, password);
    }
}
