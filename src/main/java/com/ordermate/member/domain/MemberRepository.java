package com.ordermate.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

//    @Query("select m from Member m where m.gender = :gender")
//    List<Member> any(@Param("gender")GenderType genderType);
}
