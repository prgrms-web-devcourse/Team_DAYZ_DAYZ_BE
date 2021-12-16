package com.dayz.comment.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadAllCommentsResponse {

    @NotBlank(message = "content must not be null.")
    private String content;

    @NotNull(message = "createdAt must not be null.")
    private LocalDateTime createdAt;

    @NotNull(message = "memberResult must not be null.")
    private MemberResult memberResult;

    public static ReadAllCommentsResponse of(String content, LocalDateTime createdAt, MemberResult memberResult) {
        ReadAllCommentsResponse readAllCommentsResponse = new ReadAllCommentsResponse();
        readAllCommentsResponse.setContent(content);
        readAllCommentsResponse.setCreatedAt(createdAt);
        readAllCommentsResponse.setMemberResult(memberResult);

        return readAllCommentsResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberResult {

        @NotBlank(message = "name must not be null.")
        private String name;

        @NotBlank(message = "imageUrl must not be null.")
        private String imageUrl;

        public static MemberResult of(String name, String imageUrl) {
            MemberResult memberResult = new MemberResult();
            memberResult.setName(name);
            memberResult.setImageUrl(imageUrl);

            return memberResult;
        }
    }

}
