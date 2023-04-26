package com.ordermate.comment.domain;

import com.ordermate.comment.exception.CommentException;
import com.ordermate.comment.exception.CommentExceptionType;
import com.ordermate.member.domain.Member;
import com.ordermate.participant.domain.Role;
import com.ordermate.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String content;

    @Builder
    public Comment(Post post, Member member, LocalDateTime createdAt, String content) {
        this.post = post;
        this.member = member;
        this.createdAt = createdAt;
        this.content = content;
    }

    public void checkRemoveAuthorityByMember(Member member, Role role) {
        if (!member.equals(this.member) && role == Role.GUEST) { // 본인이거나 방장일 경우에만 지울 수 있음
            throw new CommentException(CommentExceptionType.NO_AUTHORITY_COMMENT_REMOVE);
        }
    }
}
