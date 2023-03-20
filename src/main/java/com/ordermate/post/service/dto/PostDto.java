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

@Getter
public class PostDto
{
    private Long postId;
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
    private Long ownerId;
    private String ownerName;

    public PostDto(Post post) {
        this.postId = post.getId();
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
        Member owner = post.getParticipationList().stream()
                .filter(participation -> participation.getRole().equals(Role.HOST)).findAny()
                .orElseThrow(() -> new ParticipationException(ParticipationExceptionType.NOT_FOUND)).getMember();
        this.ownerName = isAnonymous ? owner.getNickname() : owner.getName();
        this.ownerId = owner.getId();
    }
}
