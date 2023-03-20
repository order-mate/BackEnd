package com.ordermate.post.controller.dto;

import com.ordermate.post.domain.SpaceType;
import com.ordermate.post.service.dto.PostSaveDto;
import com.ordermate.post.service.dto.PostUpdateDto;

import java.time.LocalDateTime;

public record UpdateRequestDto(
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

    public PostUpdateDto toServiceDto() {
        return PostUpdateDto.builder().title(title).maxPeopleNum(maxPeopleNum)
                .isAnonymous(isAnonymous).spaceType(spaceType).content(content)
                .withOrderLink(withOrderLink).pickupSpace(pickupSpace)
                .accountNum(accountNum).estimatedOrderTime(estimatedOrderTime).build();
    }
}