package com.ordermate.post.service;

import com.ordermate.member.domain.GenderType;
import com.ordermate.member.domain.Member;
import com.ordermate.member.domain.MemberRepository;
import com.ordermate.member.exception.MemberException;
import com.ordermate.member.exception.MemberExceptionType;
import com.ordermate.participant.domain.Participation;
import com.ordermate.participant.domain.Role;
import com.ordermate.post.controller.dto.DirectionType;
import com.ordermate.post.controller.dto.UploadPostAuthorityDto;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostRepository;
import com.ordermate.post.domain.PostStatus;
import com.ordermate.post.domain.SpaceType;
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

        Role role = getRole(member, post);
        if (role.equals(Role.HOST)) {
            postRepository.delete(post);
        } else if(role.equals(Role.GUEST)){
            post.leaveGuest(member);
        }
    }

    public void deletePost(Long postId, Long memberId) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        if (!getRole(member, post).equals(Role.HOST)) {
            throw new PostException(PostExceptionType.NO_AUTHORITY_DELETE);
        }

        postRepository.delete(post);
    }

    public void explodePost(Long postId, Long memberId) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.explode(member);
    }

    @Transactional(readOnly = true)
    public PostStatus getPostStatus(Long postId) {
        Post post = findPost(postId);
        return post.getPostStatus();
    }

    public void togglePostStatus(Long postId, Long memberId, DirectionType directionType, PostStatus currentStatus) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.togglePostStatus(member, directionType, currentStatus);
    }

    public void updatePost(Long postId, Long memberId, PostUpdateDto postUpdateDto) {
        Member member = findMember(memberId);
        Post post = findPost(postId);

        post.update(postUpdateDto, member);
    }

    private Role getRole(Member member, Post post) {
        return post.getParticipationMemberRole(member);
    }

    @Transactional(readOnly = true)
    public List<PostDto> getAllFilteredPost(SpaceType spaceType, GenderType genderType) {
        return postRepository.findAll().stream()
                .filter(p -> spaceType == SpaceType.ALL || p.getSpaceType().equals(spaceType))
                .filter(p -> genderType == GenderType.ALL || p.getParticipationList().stream()
                        .filter(participation -> participation.getRole() == Role.HOST)
                        .map(Participation::getMember)
                        .map(Member::getGender)
                        .anyMatch(gender -> gender.equals(genderType)))
                .map(PostDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostDetailDto getPost(Long postId, Long memberId) {
        Post post = findPost(postId);
        Member member = findMember(memberId);

        return new PostDetailDto(post, member);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public UploadPostAuthorityDto getAuthUploadPost(Long memberId) {
        List<PostDto> allParticipatedPost = getAllParticipatedPost(memberId);
        return new UploadPostAuthorityDto(allParticipatedPost.stream()
                .filter(p -> !p.getPostStatus().equals(PostStatus.END_OF_ROOM))
                .findAny().isEmpty());
    }
}
