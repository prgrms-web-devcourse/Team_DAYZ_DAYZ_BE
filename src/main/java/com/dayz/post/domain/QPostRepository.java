package com.dayz.post.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QPostRepository {

    Optional<Post> findDetailPostById(Long postId);

    Page<Post> findPostsByFollows(List<Long> ids, Pageable pageRequest);

}
