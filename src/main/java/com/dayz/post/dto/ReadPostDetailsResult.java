package com.dayz.post.dto;

import com.dayz.post.dto.ReadPostDetailResponse.AtelierResult;
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
public class ReadPostDetailsResult {

    private Long postId;

    private String content;

    private List<PostImageResult> images;

    private ReadPostDetailsResult.AtelierResult atelierResult;

    private Long oneDayClassId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static ReadPostDetailsResult of(Long postId,
            String content,
            List<PostImageResult> images,
            ReadPostDetailsResult.AtelierResult atelierResult,
            Long oneDayClassId,
            LocalDateTime createdAt) {
        ReadPostDetailsResult readPostDetailsResult = new ReadPostDetailsResult();
        readPostDetailsResult.setPostId(postId);
        readPostDetailsResult.setContent(content);
        readPostDetailsResult.setImages(images);
        readPostDetailsResult.setAtelierResult(atelierResult);
        readPostDetailsResult.setOneDayClassId(oneDayClassId);
        readPostDetailsResult.setCreatedAt(createdAt);

        return readPostDetailsResult;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostImageResult {

        private String imageUrl;

        private int sequence;

        public static ReadPostDetailsResult.PostImageResult of(String imageUrl, int sequence) {
            ReadPostDetailsResult.PostImageResult postImageResult = new ReadPostDetailsResult.PostImageResult();
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

        public static ReadPostDetailsResult.AtelierResult of(Long atelierId, String name, String imageUrl) {
            ReadPostDetailsResult.AtelierResult atelierResult = new ReadPostDetailsResult.AtelierResult();
            atelierResult.setAtelierId(atelierId);
            atelierResult.setName(name);
            atelierResult.setImageUrl(imageUrl);

            return atelierResult;
        }

    }

}
