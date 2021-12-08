package com.dayz.member.controller;

import com.dayz.common.dto.ApiResponse;
import com.dayz.common.jwt.JwtAuthentication;
import com.dayz.member.dto.ReadMemberInfoResponse;
import com.dayz.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ReadMemberInfoResponse> me(@AuthenticationPrincipal JwtAuthentication authentication) {
        ReadMemberInfoResponse memberInfo = memberService.getMemberInfo();

        return ApiResponse.<ReadMemberInfoResponse>ok(memberInfo);
    }

}
