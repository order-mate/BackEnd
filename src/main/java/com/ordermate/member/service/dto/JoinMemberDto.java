package com.ordermate.member.service.dto;

import com.ordermate.member.domain.GenderType;
import com.ordermate.member.domain.Member;
import lombok.Getter;

@Getter
public class JoinMemberDto {
    private String username;
    private String password;
    private String name;
    private String nickname;
    private GenderType gender;
    private String school;
    private String major;

    public JoinMemberDto(String username, String password, String name, String nickname, GenderType gender, String school, String major) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.school = school;
        this.major = major;
    }

    public Member toEntity() {
        return Member.builder().username(username).password(password).gender(gender).school(school).major(major).nickname(nickname).name(name).build();
    }
}
