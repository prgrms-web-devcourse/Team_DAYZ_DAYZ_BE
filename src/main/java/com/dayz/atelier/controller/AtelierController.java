package com.dayz.atelier.controller;

import com.dayz.atelier.dto.AtelierCreateRequest;
import com.dayz.atelier.service.AtelierService;
import com.dayz.common.dto.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/ateliers")
public class AtelierController {

    private final AtelierService atelierService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse createAtelier(@RequestBody @Valid AtelierCreateRequest request) {
        return ApiResponse.ok(atelierService.save(request));
    }

}