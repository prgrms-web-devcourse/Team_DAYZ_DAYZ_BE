package com.dayz.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadPostDetailResponse {

    private Long postId;

    private String content;

    private List<PostImageResult> images;

    private AtelierResult atelier;

    private Long oneDayClassId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static ReadPostDetailResponse of(Long postId,
            String content,
            List<PostImageResult> images,
            AtelierResult atelier,
            Long oneDayClassId,
            LocalDateTime createdAt) {
        ReadPostDetailResponse readPostDetailResponse = new ReadPostDetailResponse();
        readPostDetailResponse.setPostId(postId);
        readPostDetailResponse.setContent(content);
        readPostDetailResponse.setImages(images);
        readPostDetailResponse.setAtelier(atelier);
        readPostDetailResponse.setOneDayClassId(oneDayClassId);
        readPostDetailResponse.setCreatedAt(createdAt);

        return readPostDetailResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostImageResult {

        private String imageUrl;

        private int sequence;

        public static PostImageResult of(String imageUrl, int sequence) {
            PostImageResult postImageResult = new PostImageResult();
            postImageResult.setImageUrl(imageUrl);
            postImageResult.setSequence(sequence);

            return postImageResult;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AtelierResult {

        private Long atelierId;

        private String name;

        private String imageUrl;

        public static AtelierResult of(Long atelierId, String name, String imageUrl) {
            AtelierResult atelierResult = new AtelierResult();
            atelierResult.setAtelierId(atelierId);
            atelierResult.setName(name);
            atelierResult.setImageUrl(imageUrl);

            return atelierResult;
        }

    }

}
