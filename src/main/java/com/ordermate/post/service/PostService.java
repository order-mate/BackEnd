package com.ordermate.post.service;

import com.ordermate.member.domain.Member;
import com.ordermate.member.domain.MemberRepository;
import com.ordermate.member.exception.MemberException;
import com.ordermate.member.exception.MemberExceptionType;
import com.ordermate.participant.domain.Role;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostRepository;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.exception.PostException;
import com.ordermate.post.exception.PostExceptionType;
import com.ordermate.post.service.dto.PostDetailDto;
import com.ordermate.post.service.dto.PostDto;
import com.ordermate.post.service.dto.PostSaveDto;
import com.ordermate.post.service.dto.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void savePost(Long memberId, PostSaveDto postSaveDto) {
        Member member = findMember(memberId);
        Post post = postSaveDto.toEntity(member);

        postRepository.save(post);
    }

    public void addGuest(Long postId, Long memberId) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.addGuest(member);
    }

    public void leavePost(Long postId, Long memberId) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.leave(member);
    }

    public void explodePost(Long postId, Long memberId) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.explode(member);
    }

    public PostStatus getPostStatus(Long postId) {
        Post post = findPost(postId);
        return post.getPostStatus();
    }

    public void togglePostStatus(Long postId, Long memberId) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.togglePostStatus(member);
    }

    public void updatePost(Long postId, Long memberId, PostUpdateDto postUpdateDto) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.update(postUpdateDto, member);
    }

    public Role getRole(Member member, Post post) {
        return post.getParticipationMemberRole(member);
    }

//    public List<PostDto> getPostList() {
//
//    }

    // Todo 필터 기능 추가
    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream().map(PostDto::new).toList();
    }

    public PostDetailDto getPost(Long postId) {
        Post post = findPost(postId);

        return new PostDetailDto(post);
    }

    public List<PostDto> getAllParticipatedPost(Long memberId) {
        return postRepository.findAllByMemberId(memberId)
                .stream().map(PostDto::new).toList();
    }

    public void addComment() {
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND));
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));
    }
}
