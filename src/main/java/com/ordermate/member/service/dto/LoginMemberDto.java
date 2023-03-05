package com.ordermate.member.service.dto;

import lombok.Getter;

@Getter
public class LoginMemberDto {

    private String username;
    private String password;

    public LoginMemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
