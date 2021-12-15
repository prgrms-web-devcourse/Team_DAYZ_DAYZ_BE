package com.dayz.post.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {

    @NotNull(message = "postImages must not be null.")
    private List<PostImagesRequest> postImages = new ArrayList<>();

    @NotBlank(message = "content must not be null.")
    private String content;

    @NotBlank(message = "atelierId must not be null.")
    private Long atelierId;

    @NotBlank(message = "oneDayClassId must not be null.")
    private Long oneDayClassId;

    public static PostCreateRequest of(String content, Long atelierId, Long oneDayClassId, List<PostImagesRequest> postImages) {
        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setContent(content);
        postCreateRequest.setAtelierId(atelierId);
        postCreateRequest.setOneDayClassId(oneDayClassId);
        if(postImages.size() > 0) {
            postCreateRequest.setPostImages(postImages);
        }

        return postCreateRequest;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostImagesRequest {

        @NotBlank(message = "imageUrl must not be null.")
        private String imageFileName;

        @NotBlank(message = "sequence must not be null.")
        private int sequence;

        public static PostImagesRequest of(String imageFileName, int sequence) {
            PostImagesRequest postImagesRequest = new PostImagesRequest();
            postImagesRequest.setSequence(sequence);
            postImagesRequest.setImageFileName(imageFileName);

            return postImagesRequest;
        }
    }

}
