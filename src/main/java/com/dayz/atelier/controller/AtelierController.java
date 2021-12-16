package com.dayz.atelier.controller;


import com.dayz.atelier.dto.ReadAtelierDetailResponse;
import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.dto.ReadAteliersResult;
import com.dayz.atelier.dto.SaveAtelierRequest;
import com.dayz.atelier.dto.SaveAtelierResponse;
import com.dayz.atelier.service.AtelierService;
import com.dayz.common.aop.LoginMember;
import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.jwt.JwtAuthentication;
import com.dayz.member.domain.Member;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping(value = "/{atelierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ReadAtelierDetailResponse> getAtelierDetail(@PathVariable("atelierId") Long atelierId) {

        ReadAtelierDetailResponse response = atelierService.getAtelierDetail(atelierId);

        return ApiResponse.<ReadAtelierDetailResponse>ok(response);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse<ReadAteliersResult>> getAteliers(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @Valid @RequestBody CustomPageRequest request) {
        CustomPageResponse<ReadAteliersResult> response = atelierService.getAteliers(authentication.getId(), request.convertToPageRequest(Atelier.class));

        return ApiResponse.<CustomPageResponse<ReadAteliersResult>>ok(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<SaveAtelierResponse> saveAtelier(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @Valid @RequestBody SaveAtelierRequest request) {

        SaveAtelierResponse response = atelierService.savaAtelierInfo(authentication.getId(), request);

        return ApiResponse.<SaveAtelierResponse>ok(response);
    }

}
