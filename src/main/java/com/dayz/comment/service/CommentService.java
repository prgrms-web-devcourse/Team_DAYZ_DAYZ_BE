package com.dayz.comment.service;

import com.dayz.comment.converter.CommentConverter;
import com.dayz.comment.domain.CommentRepository;
import com.dayz.comment.dto.CommentCreateRequest;
import lombok.RequiredArgsConstructor;
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
}
