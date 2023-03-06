package com.ordermate.post.service.dto;

import com.ordermate.member.domain.Member;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostSaveDto(
        String title,
        Integer maxPeopleNum,
        Boolean isAnonymous,
        SpaceType spaceType,
        String content,
        String withOrderLink,
        String pickupSpace,
        String accountNum,
        LocalDateTime estimatedOrderTime
) {

    public Post toEntity(Member member) {
        return Post.builder().title(title).maxPeopleNum(maxPeopleNum)
                .isAnonymous(isAnonymous).spaceType(spaceType).content(content)
                .withOrderLink(withOrderLink).pickupSpace(pickupSpace)
                .accountNum(accountNum).estimatedOrderTime(estimatedOrderTime)
                .member(member).build();
    }
}
