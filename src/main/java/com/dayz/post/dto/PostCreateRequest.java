package com.dayz.post.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {

    private List<PostImagesRequest> postImages = new ArrayList<>();

    private String content;

    private Long atelierId;

    private Long oneDayClassId;

    public static PostCreateRequest of(String content, Long atelierId, Long oneDayClassId, List<PostImagesRequest> postImages) {
        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setContent(content);
        postCreateRequest.setAtelierId(atelierId);
        postCreateRequest.setOneDayClassId(oneDayClassId);
        postCreateRequest.setPostImages(postImages);

        return postCreateRequest;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostImagesRequest {

        private String fileName;

        private int sequence;

        public static PostImagesRequest of(String fileName, int sequence) {
            PostImagesRequest postImagesRequest = new PostImagesRequest();
            postImagesRequest.setSequence(sequence);
            postImagesRequest.setFileName(fileName);

            return postImagesRequest;
        }
    }

}
