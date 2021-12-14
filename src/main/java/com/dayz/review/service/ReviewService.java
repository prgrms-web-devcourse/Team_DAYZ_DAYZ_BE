package com.dayz.review.service;

import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.review.converter.ReviewConverter;
import com.dayz.review.domain.Review;
import com.dayz.review.domain.ReviewRepository;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse;
import com.dayz.review.dto.ReadAllMyReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewConverter reviewConverter;

    //사용자 마이페이지에서 후기 전체 조회 하는 로직
    public CustomPageResponse getAllReviews(CustomPageRequest pageRequest, Long memberId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Review.class);
        Page<ReadAllMyReviewsResponse> reviewsResponses = reviewRepository.findAllByMemberId(
                memberId,
                pageable)
            .map(reviewConverter::convertReviewResponse);

        return CustomPageResponse.of(reviewsResponses);
    }

    //    공방별 후기 목록 조회
    public CustomPageResponse getAllAtelierReviews(CustomPageRequest pageRequest, Long atelierId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Review.class);

        Page<ReadAllAtelierReviewsResponse> reviewsResponses = reviewRepository.findAllByAtelierId(
                atelierId, pageable)
            .map(reviewConverter::convertReadAllAtelierReviewsResponse);

        return CustomPageResponse.of(reviewsResponses);
    }
}
