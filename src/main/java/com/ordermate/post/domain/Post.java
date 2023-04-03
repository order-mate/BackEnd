package com.ordermate.post.domain;

import com.ordermate.comment.domain.Comment;
import com.ordermate.member.domain.Member;
import com.ordermate.participant.domain.Participation;
import com.ordermate.participant.domain.Role;
import com.ordermate.post.controller.dto.DirectionType;
import com.ordermate.post.exception.PostException;
import com.ordermate.post.exception.PostExceptionType;
import com.ordermate.post.service.dto.PostUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ordermate.post.domain.PostStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participationList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    private String title;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus = RECRUITING;

    private Integer maxPeopleNum;

    private Integer currentPeopleNum;

    private Boolean isAnonymous;

    private String content;

    private String withOrderLink;

    private String pickupSpace;

    @Enumerated(EnumType.STRING)
    private SpaceType spaceType;

    private String accountNum;

    private LocalDateTime estimatedOrderTime;

    @Builder
    public Post(String title, LocalDateTime createdAt, PostStatus postStatus, Integer maxPeopleNum, Boolean isAnonymous, String content, String withOrderLink, String pickupSpace, SpaceType spaceType, String accountNum, LocalDateTime estimatedOrderTime, Member member) {
        this.title = title;
        this.createdAt = createdAt;
        this.postStatus = postStatus;
        this.maxPeopleNum = maxPeopleNum;
        this.isAnonymous = isAnonymous;
        this.content = content;
        this.withOrderLink = withOrderLink;
        this.pickupSpace = pickupSpace;
        this.spaceType = spaceType;
        this.accountNum = accountNum;
        this.estimatedOrderTime = estimatedOrderTime;
        participationList.add(new Participation(member, this, Role.HOST));
        this.currentPeopleNum = 1;
    }


    public void addGuest(Member member) {
        if (postStatus != RECRUITING) {
            throw new PostException(PostExceptionType.NO_AUTHORITY_JOIN);
        }
        if (currentPeopleNum >= maxPeopleNum){
            throw new PostException(PostExceptionType.EXCESS_MAX_PEOPLE_NUM);
        }
        participationList.add(new Participation(member, this, Role.GUEST));
    }

    public void leaveGuest(Member member) {
        Participation participation = findParticipationByMember(member);

        participationList.remove(participation);
        currentPeopleNum--;
    }

    private Participation findParticipationByMember(Member member) {
        return participationList.stream()
                .filter(p -> p.getMember().equals(member))
                .findAny().orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));
    }

    public void togglePostStatus(Member member, DirectionType directionType, PostStatus currentStatus) {
        Participation participation = findParticipationByMember(member);

        if (participation.getRole() != Role.HOST) {
            throw new PostException(PostExceptionType.NO_AUTHORITY_STATUS_TOGGLE);
        }

        postStatus = directionType == DirectionType.NEXT ?
                next(currentStatus) : prev(currentStatus);
    }

    public Role getParticipationMemberRole(Member member) {
        Participation participation = findParticipationByMember(member);

        return participation.getRole();
    }


    public void explode(Member member) {
        Participation participation = findParticipationByMember(member);

        if (participation.getRole() != Role.HOST) {
            throw new IllegalArgumentException("호스트가 아닌 사람은 방을 종료시킬 수 없음");
        }

        postStatus = END_OF_ROOM;
    }

    public void update(PostUpdateDto postUpdateDto, Member member) {
        Participation participation = findParticipationByMember(member);

        if (participation.getRole() != Role.HOST) {
            throw new PostException(PostExceptionType.NO_AUTHORITY_UPDATE);
        }
        if (currentPeopleNum > postUpdateDto.maxPeopleNum()) {
            throw new PostException(PostExceptionType.EXCESS_MAX_PEOPLE_NUM);
        }
        title = postUpdateDto.title();
        isAnonymous = postUpdateDto.isAnonymous();
        content = postUpdateDto.content();
        withOrderLink = postUpdateDto.withOrderLink();
        pickupSpace = postUpdateDto.pickupSpace();
        spaceType = postUpdateDto.spaceType();
        accountNum = postUpdateDto.accountNum();
        maxPeopleNum = postUpdateDto.maxPeopleNum();
    }
}
