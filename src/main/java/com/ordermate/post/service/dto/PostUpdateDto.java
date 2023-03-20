package com.ordermate.post.service.dto;

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
        String accountNum,
        LocalDateTime estimatedOrderTime
) {
}
