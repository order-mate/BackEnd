package com.ordermate.member.service.dto;

import com.ordermate.member.domain.GenderType;
import com.ordermate.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfoDto{
    String name;
    String nickname;
    GenderType gender;
    String school;
    String major;

    public MemberInfoDto(Member member) {
        name = member.getName();
        nickname = member.getNickname();
        gender = member.getGender();
        school = member.getSchool();
        major = member.getMajor();
    }
}
