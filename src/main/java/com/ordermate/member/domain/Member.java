package com.ordermate.member.domain;

import com.ordermate.participant.domain.Participation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

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
