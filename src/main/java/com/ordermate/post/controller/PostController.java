package com.ordermate.post.controller;

import com.ordermate.SessionConst;
import com.ordermate.member.domain.GenderType;
import com.ordermate.member.domain.Member;
import com.ordermate.post.controller.dto.ChangeStatusRequestDto;
import com.ordermate.post.controller.dto.DirectionType;
import com.ordermate.post.controller.dto.UpdateRequestDto;
import com.ordermate.post.controller.dto.UploadRequestDto;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
import com.ordermate.post.service.PostService;
import com.ordermate.post.service.dto.PostDetailDto;
import com.ordermate.post.service.dto.PostDto;
import com.ordermate.post.service.dto.PostSaveDto;
import com.ordermate.post.service.dto.PostStatusDto;
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
    public ResponseEntity<?> getPostList(
            @RequestParam(defaultValue = "ALL", required = false) SpaceType spaceType,
            @RequestParam(defaultValue = "ALL", required = false) GenderType genderType
    ) {
        List<PostDto> postList = postService.getAllFilteredPost(spaceType, genderType);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable("postId") Long postId) {
        PostDetailDto post = postService.getPost(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody UpdateRequestDto updateRequestDto,
            HttpServletRequest request
    ) {
        Member member= (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        postService.updatePost(postId, member.getId(), updateRequestDto.toServiceDto());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable("postId") Long postId,
            HttpServletRequest request
    ){
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        postService.deletePost(postId, member.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/status")
    public ResponseEntity<?> changePostStatus(
            @PathVariable Long postId,
            @RequestBody ChangeStatusRequestDto changeStatusRequestDto,
            HttpServletRequest request
    ) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        DirectionType directionType = changeStatusRequestDto.directionType();
        PostStatus currentStatus = changeStatusRequestDto.currentStatus();

        PostStatusDto postStatusDto = postService.togglePostStatus(postId, member.getId(), directionType, currentStatus);

        return new ResponseEntity<>(postStatusDto ,HttpStatus.CREATED);
    }
}

