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
import com.ordermate.post.service.dto.PostSaveDto;
import com.ordermate.post.service.dto.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void save(Long memberId, PostSaveDto postSaveDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND));
        Post post = postSaveDto.toEntity(member);

        postRepository.save(post);
    }

    public void addGuest(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));

        post.addGuest(member);
    }

    public void leave(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));
        
        post.leave(member);
    }

    public void explode(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));

        post.explode(member);
    }

    public PostStatus getPostStatus(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));

        return post.getPostStatus();
    }

    public void togglePostStatus(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));

        post.togglePostStatus(member);
    }

    public void update(Long postId, Long memberId, PostUpdateDto postUpdateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));

        post.update(postUpdateDto, member);
    }

    public Role getRole(Member member, Post post) {
        return post.getParticipationMemberRole(member);
    }

//    public List<PostDto> getPostList() {
//
//    }

    public void addComment() {}
}
