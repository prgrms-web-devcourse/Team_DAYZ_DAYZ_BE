package com.dayz.post.converter;

import com.dayz.post.domain.Post;
import com.dayz.post.dto.PostCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public Post PostCreateRequestToPostDto(PostCreateRequest request) {
        Post post = Post.of(
                request.getContent(),
                request.getMember(),
                request.getOneDayClass());
        post.setPostImages(request.getPostImages());
        return post;
    }

}
