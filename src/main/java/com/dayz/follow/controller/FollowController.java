package com.dayz.follow.controller;

import com.dayz.common.dto.ApiResponse;
import com.dayz.follow.dto.FollowRequest;
import com.dayz.follow.service.FollowService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse followingUnfollowing(@RequestBody @Valid FollowRequest request) {
        return ApiResponse.ok(followService.followingUnfollowing(request.getMemberId(), request.getAtelierId()));
    }

}
