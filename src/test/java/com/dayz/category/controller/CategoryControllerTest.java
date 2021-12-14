package com.dayz.category.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.category.domain.Category;
import com.dayz.category.domain.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CategoryController 통합 테스트")
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        List<Category> categories = List.of(
                Category.of(1L, "도자기"),
                Category.of(2L, "네온싸인 공예"),
                Category.of(3L, "양모펠트"),
                Category.of(4L, "요리")
        );

        categoryRepository.saveAll(categories);
    }

    @Test
    @DisplayName("카테고리 목록 조회")
    void categories_success() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

}
