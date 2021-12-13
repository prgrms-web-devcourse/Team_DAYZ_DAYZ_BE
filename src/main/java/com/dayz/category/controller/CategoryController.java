package com.dayz.category.controller;

import com.dayz.category.dto.ReadAllCategoriesResponse;
import com.dayz.category.service.CategoryService;
import com.dayz.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ReadAllCategoriesResponse> categories() {
        ReadAllCategoriesResponse response = categoryService.getAllCategoryList();

        return ApiResponse.<ReadAllCategoriesResponse>ok(response);
    }

}
