package com.dayz.comment.dto;

import com.dayz.member.domain.Member;
import com.dayz.post.domain.Post;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {

//    @NotNull
    private String content;

    @NotNull
    private Post post;

    @NotNull
    private Member member;

}
