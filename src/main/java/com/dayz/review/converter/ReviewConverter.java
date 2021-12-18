package com.dayz.review.converter;

import com.dayz.common.util.ImageUrlUtil;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.review.domain.Review;
import com.dayz.review.domain.ReviewImage;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse.AtelierMemberResult;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse.AtelierOneDayClassResult;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse.AtelierReviewImageResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse;
import com.dayz.review.dto.ReadAllMyReviewsResponse.MemberResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse.OneDayClassResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse.ReviewImageResult;
import com.dayz.review.dto.ReadAllOneDayClassReviewsResponse;
import com.dayz.review.dto.ReadAllOneDayClassReviewsResponse.OneDayClassMemberResult;
import com.dayz.review.dto.ReadAllOneDayClassReviewsResponse.OneDayClassReviewImageResult;
import com.dayz.review.dto.SaveReviewRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewConverter {

    private final ImageUrlUtil imageUrlUtil;

    public ReadAllMyReviewsResponse convertReviewResponse(Review review) {

        MemberResult memberResult = MemberResult.of(review.getMember().getId(),
            review.getMember().getUsername(), review.getMember().getProfileImageUrl());

        OneDayClassResult oneDayClassResult = OneDayClassResult.of(review.getOneDayClass().getId(),
            review.getOneDayClass().getName());

        List<ReviewImageResult> reviewImageResult = review.getReviewImages().stream()
            .map(reviewImage -> ReviewImageResult.of(imageUrlUtil.makeImageUrl(reviewImage.getImageFileName()),
                reviewImage.getSequence())).collect(Collectors.toList());

        return ReadAllMyReviewsResponse.of(review.getId(), review.getContent(),
            review.getScore(), review.getCreatedAt(), memberResult, oneDayClassResult,
            reviewImageResult);
    }

    public ReadAllAtelierReviewsResponse convertReadAllAtelierReviewsResponse(Review review) {

        AtelierMemberResult atelierMemberResult = AtelierMemberResult.of(review.getMember().getId(),
            review.getMember().getUsername(), review.getMember().getProfileImageUrl());

        AtelierOneDayClassResult atelierOneDayClassResult = AtelierOneDayClassResult.of(review.getOneDayClass().getId(),
            review.getOneDayClass().getName());

        List<AtelierReviewImageResult> atelierReviewImageResult = review.getReviewImages().stream()
            .map(reviewImage -> AtelierReviewImageResult.of(imageUrlUtil.makeImageUrl(reviewImage.getImageFileName()),
                reviewImage.getSequence())).collect(Collectors.toList());

        return ReadAllAtelierReviewsResponse.of(review.getId(), review.getContent(),
            review.getScore(), review.getCreatedAt(), atelierMemberResult, atelierOneDayClassResult,
            atelierReviewImageResult);
    }

    public ReadAllOneDayClassReviewsResponse convertReadAllOneDayClassReviewsResponse(Review review) {

        OneDayClassMemberResult memberResult = OneDayClassMemberResult.of(review.getMember().getId(),
            review.getMember().getUsername(), review.getMember().getProfileImageUrl());

        List<OneDayClassReviewImageResult> reviewImageResults = review.getReviewImages().stream()
            .map(reviewImage -> OneDayClassReviewImageResult.of(imageUrlUtil.makeImageUrl(reviewImage.getImageFileName()),
                reviewImage.getSequence())).collect(Collectors.toList());

        return ReadAllOneDayClassReviewsResponse.of(review.getId(), review.getContent(),
            review.getScore(), review.getCreatedAt(),memberResult,reviewImageResults);
    }

    public Review convertReview(SaveReviewRequest saveReviewRequest, Member member,OneDayClass oneDayClass){
        List<ReviewImage> reviewImages = saveReviewRequest.getImages().stream().map(
            imageRequest -> ReviewImage.of(imageUrlUtil.extractFileName(imageRequest.getImageUrl()),
                imageRequest.getSequence())).collect(Collectors.toList());

        return Review.of(saveReviewRequest.getContent(), saveReviewRequest.getScore(),member,oneDayClass,reviewImages);
    }

}
