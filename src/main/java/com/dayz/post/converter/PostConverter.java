package com.dayz.post.converter;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import com.dayz.post.dto.PostReadAllResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostConverter {

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
        return PostImage.of(postImagesRequest.getImageUrl(), postImagesRequest.getSequence());
    }

    public PostReadAllResult convertToPostReadAllResult(Post post) {
        return PostReadAllResult.of(post.getMember().getAtelier().getId(), post.getId(), post.getPostImages().get(0).getFileName());
    }

}