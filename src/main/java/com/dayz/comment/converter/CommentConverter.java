package com.dayz.comment.converter;

import com.dayz.comment.domain.Comment;
import com.dayz.comment.dto.CommentCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public Comment CommentCreateRequestToDto(CommentCreateRequest request) {
        return Comment.of(request.getContent(), request.getPost(), request.getMember());
    }

}
