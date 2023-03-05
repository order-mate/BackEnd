package com.ordermate.member.domain;

import com.ordermate.participant.domain.Participation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Builder
    public Member(List<Participation> participationList, String username, String password, String name, String nickname, GenderType gender, String school, String major) {
        this.participationList = participationList;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.school = school;
        this.major = major;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Participation> participationList = new ArrayList<>();

    private String username;
    private String password;
    private String name;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String school;
    private String major;
}
