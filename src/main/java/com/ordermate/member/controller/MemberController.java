package com.ordermate.member.controller;

import com.ordermate.SessionConst;
import com.ordermate.member.controller.dto.JoinRequest;
import com.ordermate.member.controller.dto.LoginRequest;
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

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        memberService.login(loginRequest.toServiceDto());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, session);

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
    public ResponseEntity<?> join(@RequestBody JoinRequest joinRequest) {

        memberService.join(joinRequest.toServiceDto());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
