package com.ordermate.post.service.dto;

import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import lombok.Builder;

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
