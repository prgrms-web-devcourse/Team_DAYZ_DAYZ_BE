package com.dayz.post.domain;

import java.util.Optional;

public interface QPostRepository {

    public Optional<Post> findDetailPostById(Long postId);

}
