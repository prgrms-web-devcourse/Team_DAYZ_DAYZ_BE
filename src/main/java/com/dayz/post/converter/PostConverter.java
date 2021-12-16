package com.dayz.post.converter;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.common.util.ImageUrlUtil;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassImage;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import com.dayz.post.dto.ReadPostDetailResponse;
import com.dayz.post.dto.ReadPostDetailsResult;
import com.dayz.post.dto.ReadPostsByAtelierResult;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class PostConverter {

    private final ImageUrlUtil imageUrlUtil;

    public Post convertToPost(PostCreateRequest request, Member member, OneDayClass oneDayClass) {
        Post post = Post.of(
                request.getContent(),
                member,
                oneDayClass,
                request.getImages().stream()
                        .map(this::convertToCreatePostPostImagesRequest)
                        .collect(Collectors.toList()));

        return post;
    }

    public PostImage convertToCreatePostPostImagesRequest(PostImagesRequest postImagesRequest) {
        return PostImage.of(imageUrlUtil.extractFileName(postImagesRequest.getImageUrl()), postImagesRequest.getSequence());
    }

    public ReadPostDetailResponse convertToReadPostDetailResponse(Post post) {
        return ReadPostDetailResponse.of(
                post.getId(),
                post.getContent(),
                post.getPostImages().stream()
                        .map(this::convertToReadPostDetailPostImageResult)
                        .collect(Collectors.toList()),
                convertToReadPostDetailAtelierResult(post.getMember()),
                post.getOneDayClass().getId(),
                post.getCreatedAt()
        );
    }

    public ReadPostDetailResponse.PostImageResult convertToReadPostDetailPostImageResult(PostImage postImage) {
        return ReadPostDetailResponse.PostImageResult.of(
                imageUrlUtil.makeImageUrl(postImage.getImageFileName()),
                postImage.getSequence()
        );
    }

    public ReadPostDetailResponse.AtelierResult convertToReadPostDetailAtelierResult(Member member) {
        return ReadPostDetailResponse.AtelierResult.of(
                member.getAtelier().getId(),
                member.getAtelier().getName(),
                member.getProfileImageUrl()
        );
    }

    public ReadPostDetailsResult convertToReadPostDetailsResult(Post post) {
        return ReadPostDetailsResult.of(
                post.getId(),
                post.getContent(),
                post.getPostImages().stream()
                        .map(this::convertToReadPostDetailsPostImageResult)
                        .collect(Collectors.toList()),
                convertToReadPostDetailsAtelierResult(post.getMember()),
                post.getOneDayClass().getId(),
                post.getCreatedAt()
        );
    }

    public ReadPostDetailsResult.PostImageResult convertToReadPostDetailsPostImageResult(PostImage postImage) {
        return ReadPostDetailsResult.PostImageResult.of(
                imageUrlUtil.makeImageUrl(postImage.getImageFileName()),
                postImage.getSequence()
        );
    }

    public ReadPostDetailsResult.AtelierResult convertToReadPostDetailsAtelierResult(Member member) {
        return ReadPostDetailsResult.AtelierResult.of(
                member.getAtelier().getId(),
                member.getAtelier().getName(),
                member.getProfileImageUrl()
        );
    }

    public ReadPostsByAtelierResult convertToReadPostsByAtelierResult(Post post) {
        return ReadPostsByAtelierResult.of(
                post.getId(),
                getFirstImageUrl(post.getPostImages()),
                post.getCreatedAt()
        );
    }

    // TODO: 여러군데 중복코드 발생, 하나로 통합할 필요가 있음.. 이안에서 정렬도 하면 될듯..?
    private String getFirstImageUrl(List<PostImage> postImages) {
        if (Objects.isNull(postImages) || (postImages.size() <= 0)) {
            return null;
        }

        String firstImageFileName = postImages.get(0).getImageFileName();

        if (StringUtils.isEmpty(firstImageFileName)) {
            return null;
        }

        return imageUrlUtil.makeImageUrl(firstImageFileName);
    }

}
