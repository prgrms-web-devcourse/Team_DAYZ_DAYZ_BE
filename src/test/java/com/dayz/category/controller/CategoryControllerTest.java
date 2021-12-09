package com.dayz.category.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dayz.category.converter.CategoryConverter;
import com.dayz.category.domain.Category;
import com.dayz.category.dto.ReadAllCategoriesResponse;
import com.dayz.category.service.CategoryService;
import com.dayz.common.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CategoryController 단위 테스트")
class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("카테고리 목록 조회")
    void categories_success() throws Exception {

        // given
        List<Category> categories = List.of(
                Category.of(1L, "도자기"),
                Category.of(2L, "네온싸인 공예"),
                Category.of(3L, "양모펠트"),
                Category.of(4L, "요리")
        );

        ReadAllCategoriesResponse readAllCategoriesResponse = categoryConverter.convertToReadAllCategoryResponse(categories);
        given(categoryService.getAllCategoryList()).willReturn(readAllCategoriesResponse);

        // when // then
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(ApiResponse.<ReadAllCategoriesResponse>ok(readAllCategoriesResponse))))
                .andExpect(status().isOk());
    }

}
