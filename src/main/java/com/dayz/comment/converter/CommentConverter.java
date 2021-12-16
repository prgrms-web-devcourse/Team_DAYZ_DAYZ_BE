package com.dayz.comment.converter;

import com.dayz.comment.domain.Comment;
import com.dayz.comment.dto.ReadAllCommentsResult;
import com.dayz.comment.dto.ReadAllCommentsResult.MemberResult;
import com.dayz.member.domain.Member;
import com.dayz.post.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public Comment CommentCreateRequestToDto(String content, Post post, Member member) {
        return Comment.of(content, post, member);
    }

    public ReadAllCommentsResult convertReadAllCommentsResult(Comment comment) {
        return ReadAllCommentsResult.of(comment.getContent(), comment.getCreatedAt(), convertReadAllCommentsMemberResult(comment.getMember()));
    }

    public ReadAllCommentsResult.MemberResult convertReadAllCommentsMemberResult(Member member) {
        return ReadAllCommentsResult.MemberResult.of(member.getUsername(), member.getProfileImageUrl());
    }

}
