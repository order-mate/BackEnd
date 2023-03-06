package com.ordermate.post.service.dto;

import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record PostDto(
        String title,
        LocalDateTime createdAt,
        PostStatus postStatus,
        Integer maxPeopleNum,
        Integer currentPeopleNum,
        Boolean isAnonymous,
        String content,
        String pickupSpace,
        SpaceType spaceType,
        LocalDateTime estimatedOrderTime
) {

}
