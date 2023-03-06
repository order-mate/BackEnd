package com.ordermate.post.service;

import com.ordermate.member.domain.Member;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostSaveDto(
        String title,
        LocalDateTime createdAt,
        PostStatus postStatus,
        Integer maxPeopleNum,
        Integer currentPeopleNum,
        Boolean isAnonymous,
        SpaceType spaceType,
        String content,
        String withOrderLink,
        String pickupSpace,
        String accountNum,
        LocalDateTime estimatedOrderTime
) {

    public Post toEntity(Member member) {
        return Post.builder().title(title).createdAt(createdAt)
                .postStatus(postStatus).maxPeopleNum(maxPeopleNum)
                .isAnonymous(isAnonymous)
                .spaceType(spaceType).content(content)
                .withOrderLink(withOrderLink).pickupSpace(pickupSpace)
                .accountNum(accountNum).estimatedOrderTime(estimatedOrderTime)
                .member(member).build();
    }
}
