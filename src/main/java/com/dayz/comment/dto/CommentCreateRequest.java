package com.dayz.comment.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {

    @NotNull(message = "postId must not be null.")
    private Long postId;

    @NotNull(message = "atelierId must not be null.")
    private Long atelierId;

    @NotNull(message = "comment must not be null or blank.")
    private String content;

}
