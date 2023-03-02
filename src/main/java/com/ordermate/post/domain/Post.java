package com.ordermate.post.domain;

import com.ordermate.comment.domain.Comment;
import com.ordermate.participant.domain.Participation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Participation> participationList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    private String title;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    private Integer maxPeopleNum;

    private Integer currentPeopleNum;

    private Boolean isAnonymous;

    private String content;

    private String withOrderLink;

    private String pickupSpace;

    @Enumerated(EnumType.STRING)
    private SpaceType spaceType;

    private String accountNum;

    private LocalDateTime estimatedOrderTime;

}
