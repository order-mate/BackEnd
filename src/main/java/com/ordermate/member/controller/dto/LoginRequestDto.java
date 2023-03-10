package com.ordermate.member.controller.dto;

import com.ordermate.member.service.dto.LoginMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String username;
    private String password;

    public LoginMemberDto toServiceDto() {
        return new LoginMemberDto(username, password);
    }
}
