package com.ordermate.member.service;

import com.ordermate.member.domain.Member;
import com.ordermate.member.domain.MemberRepository;
import com.ordermate.member.exception.MemberException;
import com.ordermate.member.service.dto.JoinMemberDto;
import com.ordermate.member.service.dto.LoginMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ordermate.member.exception.MemberExceptionType.DUPLICATE_USERNAME;
import static com.ordermate.member.exception.MemberExceptionType.NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(JoinMemberDto joinMemberDto) {
        checkDuplicateUsername(joinMemberDto.getUsername());

        Member member = joinMemberDto.toEntity();

        memberRepository.save(member);
    }

    public Member login(LoginMemberDto loginMemberDto) {
        Optional<Member> username = memberRepository.findByUsername(loginMemberDto.getUsername());

        return username.filter(m -> m.getPassword()
                        .equals(loginMemberDto.getPassword()))
                .orElseThrow(() -> new MemberException(NOT_FOUND));
    }

    private void checkDuplicateUsername(String username) {
        memberRepository.findByUsername(username).ifPresent((member)-> {
            throw new MemberException(DUPLICATE_USERNAME);
        } );
    }
}
