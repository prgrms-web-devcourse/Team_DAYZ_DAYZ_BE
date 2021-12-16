package com.dayz.comment.converter;

import com.dayz.comment.domain.Comment;
import com.dayz.comment.dto.CommentCreateRequest;
import com.dayz.comment.dto.ReadAllCommentsResponse;
import com.dayz.comment.dto.ReadAllCommentsResponse.MemberResult;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public Comment CommentCreateRequestToDto(CommentCreateRequest request) {
        return Comment.of(request.getContent(), request.getPost(), request.getMember());
    }

    public ReadAllCommentsResponse convertToCommentsResult(Comment comment) {
        MemberResult memberResult = MemberResult.of(comment.getMember().getUsername(), comment.getMember().getProfileImageUrl());

        return ReadAllCommentsResponse.of(comment.getContent(), comment.getCreatedAt(), memberResult);
    }

}