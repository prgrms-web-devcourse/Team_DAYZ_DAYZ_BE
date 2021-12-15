package com.dayz.post.converter;

import com.dayz.common.util.ImageUrlUtil;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostConverter {

    private final ImageUrlUtil imageUrlUtil;

    public Post convertToPost(String content, Member member, OneDayClass oneDayClass, List<PostImagesRequest> postImagesRequests) {

        Post post = Post.of(
                content,
                member,
                oneDayClass);
        post.addPostImages(postImagesRequests.stream().map(this::convertToImage)
                .collect(Collectors.toList()));
        return post;
    }

    public PostImage convertToImage(PostImagesRequest postImagesRequest) {
        return PostImage.of(imageUrlUtil.extractFileName(postImagesRequest.getImageFileName()), postImagesRequest.getSequence());
    }

}