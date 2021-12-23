package com.dayz.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class ReadAllAtelierReviewsResponse {

    private Long id;

    private String content;

    private int score;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    private AtelierMemberResult member;

    private AtelierOneDayClassResult oneDayClass;

    private List<AtelierReviewImageResult> reviewImages;

    public static ReadAllAtelierReviewsResponse of(Long id, String content, int score,
        LocalDateTime createdAt,
        AtelierMemberResult member, AtelierOneDayClassResult oneDayClass, List<AtelierReviewImageResult> reviewImages) {
        ReadAllAtelierReviewsResponse reviewResponse = new ReadAllAtelierReviewsResponse();
        reviewResponse.setId(id);
        reviewResponse.setContent(content);
        reviewResponse.setScore(score);
        reviewResponse.setCreatedAt(createdAt);
        reviewResponse.setMember(member);
        reviewResponse.setOneDayClass(oneDayClass);
        reviewResponse.setReviewImages(reviewImages);

        return reviewResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class AtelierMemberResult {

        private Long id;

        private String username;

        private String profileImageUrl;

        public static AtelierMemberResult of(Long id, String username, String profileImageUrl) {
            AtelierMemberResult atelierMemberResult = new AtelierMemberResult();
            atelierMemberResult.setId(id);
            atelierMemberResult.setUsername(username);
            atelierMemberResult.setProfileImageUrl(profileImageUrl);

            return atelierMemberResult;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class AtelierOneDayClassResult {

        private Long id;

        private String name;

        public static AtelierOneDayClassResult of(Long id, String name) {
            AtelierOneDayClassResult atelierOneDayClassResult = new AtelierOneDayClassResult();
            atelierOneDayClassResult.setId(id);
            atelierOneDayClassResult.setName(name);

            return atelierOneDayClassResult;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class AtelierReviewImageResult {

        private String imageurl;

        private int sequence;

        public static AtelierReviewImageResult of(String imageurl, int sequence) {
            AtelierReviewImageResult atelierReviewImageResult = new AtelierReviewImageResult();
            atelierReviewImageResult.setImageurl(imageurl);
            atelierReviewImageResult.setSequence(sequence);
            return atelierReviewImageResult;
        }

    }

}



