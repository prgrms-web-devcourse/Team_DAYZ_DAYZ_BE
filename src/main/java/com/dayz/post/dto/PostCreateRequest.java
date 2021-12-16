package com.dayz.post.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {

    @NotNull(message = "atelierId must be not null")
    private Long atelierId;

    @NotNull(message = "oneDayClassId must be not null")
    private Long oneDayClassId;

    private String content;

    @Valid
    @NotNull(message = "postImages must be not null")
    private List<PostImagesRequest> images = new ArrayList<>();

    public static PostCreateRequest of(String content, Long atelierId, Long oneDayClassId, List<PostImagesRequest> images) {
        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setContent(content);
        postCreateRequest.setAtelierId(atelierId);
        postCreateRequest.setOneDayClassId(oneDayClassId);
        if(images.size() > 0) {
            postCreateRequest.setImages(images);
        }

        return postCreateRequest;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostImagesRequest {

        @NotBlank(message = "imageUrl must be not blank")
        private String imageUrl;

        @Min(value = 1, message = "sequence must be greater than 1")
        @Positive(message = "sequence must be positive")
        private int sequence;

        public static PostImagesRequest of(String imageUrl, int sequence) {
            PostImagesRequest postImagesRequest = new PostImagesRequest();
            postImagesRequest.setSequence(sequence);
            postImagesRequest.setImageUrl(imageUrl);

            return postImagesRequest;
        }
    }

}
