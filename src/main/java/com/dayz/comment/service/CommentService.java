package com.dayz.comment.service;

import com.dayz.comment.converter.CommentConverter;
import com.dayz.comment.domain.Comment;
import com.dayz.comment.domain.CommentRepository;
import com.dayz.comment.dto.CommentCreateRequest;
import com.dayz.comment.dto.ReadAllCommentsResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentConverter commentConverter;

    @Transactional
    public Long save(CommentCreateRequest request) {
        return commentRepository.save(commentConverter.CommentCreateRequestToDto(request)).getId();
    }

    @Transactional(readOnly = true)
    public CustomPageResponse getAllComments(CustomPageRequest pageRequest, Long postId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Comment.class);
        Page<ReadAllCommentsResponse> readAllCommentsResponses =
                commentRepository
                        .findAllByPostId(postId, pageable)
                        .map(commentConverter::convertToCommentsResult);

        return CustomPageResponse.of(readAllCommentsResponses);
    }
}