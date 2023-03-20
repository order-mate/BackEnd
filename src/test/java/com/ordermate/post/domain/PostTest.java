package com.ordermate.post.domain;

import com.ordermate.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Post 는")
class PostTest {

    @Test
    @DisplayName("방을 삭제하는 것은 HOST 만이 가능하다")
    public void explode() throws Exception {
        // given
        Member host = Member.builder().build();
        Post post = Post.builder().title("title").createdAt(LocalDateTime.now())
                .postStatus(PostStatus.RECRUITING).maxPeopleNum(100)
                .isAnonymous(false)
                .spaceType(SpaceType.DORMITORY).content("content")
                .withOrderLink("withOrderLink").pickupSpace("pickupSpace")
                .accountNum("accountNum").estimatedOrderTime(LocalDateTime.now())
                .member(host).build();

        // when & then
        assertDoesNotThrow(() -> post.explode(host));
    }

    @Test
    @DisplayName("해당 포스트에 속한 참여자가 아닌 경우 방을 제거하려 하면 예외가 발생한다")
    public void explode_fail_1() throws Exception {
        // given
        Member host = Member.builder().build();
        Member other = Member.builder().build();
        Post post = Post.builder().title("title").createdAt(LocalDateTime.now())
                .postStatus(PostStatus.RECRUITING).maxPeopleNum(100)
                .isAnonymous(false)
                .spaceType(SpaceType.DORMITORY).content("content")
                .withOrderLink("withOrderLink").pickupSpace("pickupSpace")
                .accountNum("accountNum").estimatedOrderTime(LocalDateTime.now())
                .member(host).build();

        // when & then
        assertThatThrownBy(() -> post.explode(other))
                .isInstanceOf(RuntimeException.class);
    }


    @Test
    @DisplayName("HOST가 아닌 사람이 방을 제거하려 하면 예외가 발생한다")
    public void explode_fail_2() throws Exception {
        // given
        Member host = Member.builder().build();
        Member other = Member.builder().build();
        Post post = Post.builder().title("title").createdAt(LocalDateTime.now())
                .postStatus(PostStatus.RECRUITING).maxPeopleNum(100)
                .isAnonymous(false)
                .spaceType(SpaceType.DORMITORY).content("content")
                .withOrderLink("withOrderLink").pickupSpace("pickupSpace")
                .accountNum("accountNum").estimatedOrderTime(LocalDateTime.now())
                .member(host).build();
        post.addGuest(other);

        // when & then
        assertThatThrownBy(() -> post.explode(other))
                .isInstanceOf(IllegalArgumentException.class);
    }
}