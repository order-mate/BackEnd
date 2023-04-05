package com.ordermate.member.service;

import com.ordermate.member.domain.Member;
import com.ordermate.member.domain.MemberRepository;
import com.ordermate.member.exception.MemberException;
import com.ordermate.member.exception.MemberExceptionType;
import com.ordermate.member.service.dto.JoinMemberDto;
import com.ordermate.member.service.dto.LoginMemberDto;
import com.ordermate.member.service.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ordermate.member.exception.MemberExceptionType.*;

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
        Member member = memberRepository.findByUsername(loginMemberDto.getUsername()).orElseThrow(() -> new MemberException(NOT_FOUND));

        boolean isPasswordValid = member.getPassword().equals(loginMemberDto.getPassword());
        if (!isPasswordValid) {
            throw new MemberException(MemberExceptionType.PASSWORD_AUTHENTICATION_FAILED);
        }
        return member;
    }

    public MemberInfoDto findMemberInfo(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new MemberException(NOT_FOUND));

        return new MemberInfoDto(member);
    }

    private void checkDuplicateUsername(String username) {
        memberRepository.findByUsername(username).ifPresent((member) -> {
            throw new MemberException(DUPLICATE_USERNAME);
        });
    }
}
