package com.ordermate.post.service;

import com.ordermate.member.domain.Member;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostUpdateDto(
        String title,
        PostStatus postStatus,
        Integer maxPeopleNum,
        Boolean isAnonymous,
        SpaceType spaceType,
        String content,
        String withOrderLink,
        String pickupSpace,
        String accountNum
) {

}
