package com.dayz.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadPostsByAtelierResult {

    private Long postId;

    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static ReadPostsByAtelierResult of(Long postId, String imageUrl, LocalDateTime createdAt) {
        ReadPostsByAtelierResult readPostsByAtelierResult = new ReadPostsByAtelierResult();
        readPostsByAtelierResult.setPostId(postId);
        readPostsByAtelierResult.setImageUrl(imageUrl);
        readPostsByAtelierResult.setCreatedAt(createdAt);

        return readPostsByAtelierResult;
    }

}
