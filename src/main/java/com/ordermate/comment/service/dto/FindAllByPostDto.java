package com.ordermate.comment.service.dto;

import com.ordermate.comment.domain.Comment;
import com.ordermate.participant.domain.Participation;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FindAllByPostDto(
        List<Participation> participationList,
        List<Comment> commentList,
        String title,
        LocalDateTime createdAt,
        PostStatus postStatus,
        Integer maxPeopleNum,
        Integer currentPeopleNum,
        Boolean isAnonymous,
        String content,
        String withOrderLink,
        String pickupSpace,
        SpaceType spaceType,
        String accountNum,
        LocalDateTime estimatedOrderTime
) {
}
