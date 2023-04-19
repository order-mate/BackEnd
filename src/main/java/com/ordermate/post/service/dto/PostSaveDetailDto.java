package com.ordermate.post.service.dto;

import com.ordermate.member.domain.Member;
import com.ordermate.participant.domain.Role;
import com.ordermate.participant.exception.ParticipationException;
import com.ordermate.participant.exception.ParticipationExceptionType;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostSaveDetailDto {
    private Long postId;
    private String loginUsername;
    private String ownerName;
    private String title;
    private LocalDateTime createdAt;
    private PostStatus postStatus;
    private Integer maxPeopleNum;
    private Integer currentPeopleNum;
    private Boolean isAnonymous;
    private String content;
    private String withOrderLink;
    private String pickupSpace;
    private SpaceType spaceType;
    private String accountNum;
    private LocalDateTime estimatedOrderTime;
    private List<PostDetailParticipationDto> participationList;

    public PostSaveDetailDto(Post post, Member member) {
        this.postId = post.getId();
        this.loginUsername = member.getUsername();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.postStatus = post.getPostStatus();
        this.maxPeopleNum = post.getMaxPeopleNum();
        this.currentPeopleNum = post.getCurrentPeopleNum();
        this.isAnonymous = post.getIsAnonymous();
        this.content = post.getContent();
        this.withOrderLink = post.getWithOrderLink();
        this.pickupSpace = post.getPickupSpace();
        this.spaceType = post.getSpaceType();
        this.accountNum = post.getAccountNum();
        this.estimatedOrderTime = post.getEstimatedOrderTime();

        this.participationList = post.getParticipationList().stream()
                .map(p -> new PostDetailParticipationDto(p.getRole(), p.getMember(), isAnonymous)).toList();

        Member owner = post.getParticipationList().stream()
                .filter(participation -> participation.getRole().equals(Role.HOST)).findAny()
                .orElseThrow(() -> new ParticipationException(ParticipationExceptionType.HOST_NOT_FOUND)).getMember();
        this.ownerName = isAnonymous ? owner.getNickname() : owner.getName();
    }
}
