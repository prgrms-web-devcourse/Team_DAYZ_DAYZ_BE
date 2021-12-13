package com.dayz.post.converter;

import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public Post convertToPost(PostCreateRequest request) {
        Post post = Post.of(
                request.getContent(),
                request.getAtelierId(),
                request.getOneDayClassId());
        post.addPostImages(request.getPostImages().stream().map(this::convertToImage)
                .collect(Collectors.toList()));
        return post;
    }

    public PostImage convertToImage(PostImagesRequest postImagesRequest) {
        return PostImage.of(postImagesRequest.getFileName(), postImagesRequest.getSequence());
    }

}
