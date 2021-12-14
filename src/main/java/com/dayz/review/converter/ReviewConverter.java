package com.dayz.review.converter;

import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.reservation.domain.ReservationRepository;
import com.dayz.review.domain.Review;
import com.dayz.review.domain.ReviewImage;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse;
import com.dayz.review.dto.ReadAllMyReviewsResponse;
import com.dayz.review.dto.ReadAllMyReviewsResponse.MemberResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse.OneDayClassResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse.ReviewImageResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewConverter {

    private final ReservationRepository reservationRepository;

    public ReadAllMyReviewsResponse convertReviewResponse(Review review) {

        MemberResult memberResult = MemberResult.of(review.getMember().getId(),
            review.getMember().getUsername(), review.getMember().getProfileImageUrl());

        OneDayClassResult oneDayClassResult = OneDayClassResult.of(review.getOneDayClass().getId(),
            review.getOneDayClass().getName());

        List<ReviewImageResult> reviewImageResult = review.getReviewImage().stream()
            .map(reviewImage -> ReviewImageResult.of(reviewImage.getImageFileName(),
                reviewImage.getSequence())).collect(Collectors.toList());

        return ReadAllMyReviewsResponse.of(review.getId(), review.getTitle(), review.getContent(),
            review.getScore(), review.getCreatedAt(), memberResult, oneDayClassResult,
            reviewImageResult);
    }

    public ReadAllAtelierReviewsResponse convertReadAllAtelierReviewsResponse(Review review) {

        com.dayz.review.dto.ReadAllAtelierReviewsResponse.MemberResult memberResult = com.dayz.review.dto.ReadAllAtelierReviewsResponse.MemberResult.of(review.getMember().getId(),
            review.getMember().getUsername(), review.getMember().getProfileImageUrl());

        com.dayz.review.dto.ReadAllAtelierReviewsResponse.OneDayClassResult oneDayClassResult = com.dayz.review.dto.ReadAllAtelierReviewsResponse.OneDayClassResult.of(review.getOneDayClass().getId(),
            review.getOneDayClass().getName());

        List<com.dayz.review.dto.ReadAllAtelierReviewsResponse.ReviewImageResult> reviewImageResult = review.getReviewImage().stream()
            .map(reviewImage -> com.dayz.review.dto.ReadAllAtelierReviewsResponse.ReviewImageResult.of(reviewImage.getImageFileName(),
                reviewImage.getSequence())).collect(Collectors.toList());

        return ReadAllAtelierReviewsResponse.of(review.getId(), review.getTitle(), review.getContent(),
            review.getScore(), review.getCreatedAt(), memberResult, oneDayClassResult,
            reviewImageResult);
    }

}
