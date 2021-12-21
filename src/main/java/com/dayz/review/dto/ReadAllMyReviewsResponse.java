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
public class ReadAllMyReviewsResponse {

    private Long id;

    private String content;

    private int score;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    private MemberResult member;

    private OneDayClassResult oneDayClass;

    private List<ReviewImageResult> reviewImage;

    public static ReadAllMyReviewsResponse of(Long id, String content, int score,
        LocalDateTime createdAt,
        MemberResult member, OneDayClassResult oneDayClass, List<ReviewImageResult> reviewImages) {
        ReadAllMyReviewsResponse reviewResponse = new ReadAllMyReviewsResponse();
        reviewResponse.setId(id);
        reviewResponse.setContent(content);
        reviewResponse.setScore(score);
        reviewResponse.setCreatedAt(createdAt);
        reviewResponse.setMember(member);
        reviewResponse.setOneDayClass(oneDayClass);
        reviewResponse.setReviewImage(reviewImages);

        return reviewResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class MemberResult {

        private Long id;

        private String username;

        private String profileImageUrl;

        public static MemberResult of(Long id, String username, String profileImageUrl) {
            MemberResult memberResult = new MemberResult();
            memberResult.setId(id);
            memberResult.setUsername(username);
            memberResult.setProfileImageUrl(profileImageUrl);

            return memberResult;
        }
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class OneDayClassResult {

        private Long id;

        private String name;

        public static OneDayClassResult of(Long id, String name) {
            OneDayClassResult oneDayClassResult = new OneDayClassResult();
            oneDayClassResult.setId(id);
            oneDayClassResult.setName(name);

            return oneDayClassResult;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class ReviewImageResult {

        private String imageUrl;

        private int sequence;

        public static ReviewImageResult of(String imageurl, int sequence) {
            ReviewImageResult reviewImageResult = new ReviewImageResult();
            reviewImageResult.setImageUrl(imageurl);
            reviewImageResult.setSequence(sequence);
            return reviewImageResult;
        }

    }

}



