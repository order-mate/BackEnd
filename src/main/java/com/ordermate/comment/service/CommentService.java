package com.ordermate.comment.service;

import com.ordermate.comment.domain.Comment;
import com.ordermate.comment.domain.CommentRepository;
import com.ordermate.comment.exception.CommentException;
import com.ordermate.comment.exception.CommentExceptionType;
import com.ordermate.comment.service.dto.CommentAddDto;
import com.ordermate.comment.service.dto.CommentDto;
import com.ordermate.member.domain.Member;
import com.ordermate.member.domain.MemberRepository;
import com.ordermate.member.exception.MemberException;
import com.ordermate.member.exception.MemberExceptionType;
import com.ordermate.participant.domain.Role;
import com.ordermate.post.domain.Post;
import com.ordermate.post.domain.PostRepository;
import com.ordermate.post.exception.PostException;
import com.ordermate.post.exception.PostExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public void add(Long postId, Long memberId, CommentAddDto commentAddDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND));
        Comment comment = commentAddDto.toEntity(member, post);

        commentRepository.save(comment);
    }

    public void remove(Long commentId, Long memberId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND));

        Role role = comment.getPost().getParticipationMemberRole(member);

        comment.checkRemoveAuthorityByMember(member, role); // 방장 또는 본인만 제거 권한있음

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> findAllByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND));

        List<CommentDto> commentDtoList = post.getCommentList().stream().map((comment) -> (
                        new CommentDto(comment))).toList();

        return commentDtoList;
    }
}
