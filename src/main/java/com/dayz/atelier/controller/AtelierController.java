package com.dayz.atelier.controller;

import com.dayz.atelier.dto.SaveAtelierRequest;
import com.dayz.atelier.dto.SaveAtelierResponse;
import com.dayz.atelier.service.AtelierService;
import com.dayz.common.aop.LoginMember;
import com.dayz.common.dto.ApiResponse;
import com.dayz.member.domain.Member;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ateliers")
public class AtelierController {

    private final AtelierService atelierService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<SaveAtelierResponse> saveAtelier(@LoginMember Member member, @Valid @RequestBody SaveAtelierRequest request) {

        SaveAtelierResponse response = atelierService.savaAtelierInfo(member.getId(), request);

        return ApiResponse.<SaveAtelierResponse>ok(response);
    }

}
