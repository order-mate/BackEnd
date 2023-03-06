package com.ordermate.post.controller;

import com.ordermate.SessionConst;
import com.ordermate.member.domain.Member;
import com.ordermate.member.service.MemberService;
import com.ordermate.post.controller.dto.UploadRequestDto;
import com.ordermate.post.service.dto.PostSaveDto;
import com.ordermate.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
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
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @PostMapping("/post/upload")
    public ResponseEntity<?> upload(
            @RequestBody UploadRequestDto uploadRequestDto,
            HttpServletRequest request
    ) {
        Member member= (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        PostSaveDto postSaveDto = uploadRequestDto.toServiceDto();
        postService.save(member.getId(), postSaveDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post")
    public ResponseEntity<?> list(
            HttpServletRequest request
    ) {
        postService.
    }
}
