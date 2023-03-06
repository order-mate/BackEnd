package com.ordermate.post.controller.dto;


import com.ordermate.post.domain.SpaceType;
import com.ordermate.post.service.dto.PostSaveDto;

import java.time.LocalDateTime;

public record UploadRequestDto(
    String title,
    Integer maxPeopleNum,
    Boolean isAnonymous,
    SpaceType spaceType,
    String content,
    String withOrderLink,
    String pickupSpace,
    String accountNum,
    LocalDateTime estimatedOrdTime
    ) {

    public PostSaveDto toServiceDto() {
        return PostSaveDto.builder().title(title).maxPeopleNum(maxPeopleNum)
                .isAnonymous(isAnonymous).spaceType(spaceType).content(content)
                .withOrderLink(withOrderLink).pickupSpace(pickupSpace)
                .accountNum(accountNum).estimatedOrderTime(estimatedOrdTime).build();
    }
}
