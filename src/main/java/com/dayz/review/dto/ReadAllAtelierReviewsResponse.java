package com.dayz.review.dto;

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

    private String title;

    private String content;

    private int score;

    private LocalDateTime createdAt;

    private MemberResult member;

    private OneDayClassResult oneDayClass;

    private List<ReviewImageResult> reviewImage;

    public static ReadAllAtelierReviewsResponse of(Long id, String title, String content, int score,
        LocalDateTime createdAt,
        MemberResult member, OneDayClassResult oneDayClass, List<ReviewImageResult> reviewImages) {
        ReadAllAtelierReviewsResponse reviewResponse = new ReadAllAtelierReviewsResponse();
        reviewResponse.setId(id);
        reviewResponse.setTitle(title);
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

        private String imageurl;

        private int sequence;

        public static ReviewImageResult of(String imageurl, int sequence) {
            ReviewImageResult reviewImageResult = new ReviewImageResult();
            reviewImageResult.setImageurl(imageurl);
            reviewImageResult.setSequence(sequence);
            return reviewImageResult;
        }
    }
}



