package com.ordermate.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p join fetch Participation p2" +
            " where p2.member.id = :memberId and p.id = p2.post.id")
    List<Post> findAllByMemberId(Long memberId);
}
