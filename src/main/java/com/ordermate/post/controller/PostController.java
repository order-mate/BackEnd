package com.ordermate.post.controller;

import com.ordermate.SessionConst;
import com.ordermate.member.domain.Member;
import com.ordermate.post.controller.dto.UploadRequestDto;
import com.ordermate.post.service.PostService;
import com.ordermate.post.service.dto.PostDetailDto;
import com.ordermate.post.service.dto.PostDto;
import com.ordermate.post.service.dto.PostSaveDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/upload")
    public ResponseEntity<?> upload(
            @RequestBody UploadRequestDto uploadRequestDto,
            HttpServletRequest request
    ) {
        Member member= (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        PostSaveDto postSaveDto = uploadRequestDto.toServiceDto();
        postService.savePost(member.getId(), postSaveDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post")
    public ResponseEntity<?> postList() {
        List<PostDto> postList = postService.getAllPost();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable("postId") Long postId) {
        PostDetailDto post = postService.getPost(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}

