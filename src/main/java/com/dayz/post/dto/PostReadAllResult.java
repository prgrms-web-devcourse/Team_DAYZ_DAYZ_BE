package com.dayz.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReadAllResult {

    private Long atelierId;

    private Long postId;

    private String imageUrl;

    public static PostReadAllResult of(Long atelierId, Long postId, String imageUrl) {
        PostReadAllResult postReadAllRequest = new PostReadAllResult();
        postReadAllRequest.setAtelierId(atelierId);
        postReadAllRequest.setPostId(postId);
        postReadAllRequest.setImageUrl(imageUrl);

        return postReadAllRequest;
    }

}
