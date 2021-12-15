package com.dayz.review.controller;

import com.dayz.common.aop.LoginMember;
import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.member.domain.Member;
import com.dayz.review.dto.SaveReviewRequest;
import com.dayz.review.service.ReviewService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(value = "/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse> getReviews(@LoginMember Member member,
        @RequestBody CustomPageRequest pageRequest) {
        return ApiResponse.ok(reviewService.getAllReviews(pageRequest, member.getId()));
    }

    @GetMapping(value = "/reviews/ateliers/{atelierId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse> getAtelierReviews(
        @PathVariable("atelierId") Long atelierId, @RequestBody CustomPageRequest pageRequest) {
        return ApiResponse.ok(reviewService.getAllAtelierReviews(pageRequest, atelierId));
    }

    @PostMapping(value = "/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Map<String,Long>> saveReviews(@LoginMember Member member,@Valid @RequestBody SaveReviewRequest saveReviewRequest) {
        return ApiResponse.ok(Map.of("reviewId",reviewService.saveReview(saveReviewRequest, member)));
    }
}
