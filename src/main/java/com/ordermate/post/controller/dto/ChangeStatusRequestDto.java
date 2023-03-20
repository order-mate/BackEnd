package com.ordermate.post.controller.dto;

import com.ordermate.post.domain.PostStatus;

public record ChangeStatusRequestDto(
        DirectionType directionType,
        PostStatus currentStatus
) {
}
