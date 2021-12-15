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

    @NotNull(message = "comment must not be null or blank.")
    private String content;

    @NotNull(message = "post must not be null.")
    private Post post;

    @NotNull(message = "post must not be null.")
    private Member member;

}
