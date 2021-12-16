package com.dayz.comment.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QCommentRepository {

    Page<Comment> findAllByPostId(Long postId, Pageable pageRequest);

}
