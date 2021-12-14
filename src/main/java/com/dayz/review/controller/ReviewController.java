package com.dayz.review.controller;

import com.dayz.common.aop.LoginMember;
import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.member.domain.Member;
import com.dayz.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/reviews/atelier/{atelierId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse> getAtelierReviews(
        @PathVariable("atelierId") Long atelierId, @RequestBody CustomPageRequest pageRequest) {
        return ApiResponse.ok(reviewService.getAllAtelierReviews(pageRequest, atelierId));
    }
}
