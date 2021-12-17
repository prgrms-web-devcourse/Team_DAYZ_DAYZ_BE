package com.dayz.comment.service;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.comment.converter.CommentConverter;
import com.dayz.comment.domain.Comment;
import com.dayz.comment.domain.CommentRepository;
import com.dayz.comment.dto.CommentCreateRequest;
import com.dayz.comment.dto.ReadAllCommentsResult;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final AtelierRepository atelierRepository;

    private final MemberRepository memberRepository;

    private final CommentConverter commentConverter;

    @Transactional
    public Long save(Long memberId, CommentCreateRequest request) {

        Atelier foundAtelier = atelierRepository.findById(request.getAtelierId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));

        Post foundPost = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));

        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        return commentRepository.save(commentConverter.CommentCreateRequestToDto(request.getContent(), foundPost, foundMember)).getId();
    }

    public CustomPageResponse getAllComments(CustomPageRequest pageRequest, Long postId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Comment.class);

        Page<Comment> allByPostId = commentRepository.findAllByPostId(postId, pageable);

        Page<ReadAllCommentsResult> readAllCommentsResponses =
                commentRepository.findAllByPostId(postId, pageable)
                        .map(comment -> commentConverter.convertReadAllCommentsResult(comment));

        return CustomPageResponse.of(readAllCommentsResponses);
    }

}
