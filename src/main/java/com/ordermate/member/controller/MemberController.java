package com.ordermate.member.controller;

import com.ordermate.SessionConst;
import com.ordermate.member.controller.dto.JoinRequestDto;
import com.ordermate.member.controller.dto.LoginRequestDto;
import com.ordermate.member.domain.Member;
import com.ordermate.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        Member member = memberService.login(loginRequestDto.toServiceDto());

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        log.info("sessionId: {}, member: {}", session.getId(), session.getAttribute(SessionConst.LOGIN_MEMBER));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinRequestDto joinRequestDto) {

        memberService.join(joinRequestDto.toServiceDto());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
