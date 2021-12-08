package com.dayz.category.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.dayz.category.domain.Category;
import com.dayz.category.domain.CategoryRepository;
import com.dayz.category.dto.ReadAllCategoriesResponse;
import com.dayz.category.dto.ReadAllCategoriesResponse.ReadAllCategoryResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("CategoryService 단위테스트")
@SpringBootTest
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("모든 카테고리 목록을 조회 힐 수 있다.")
    void getAllCategoryList_success() {
        // given
        List<Category> categories = List.of(
                Category.of(1L, "도자기"),
                Category.of(2L, "네온싸인 공예"),
                Category.of(3L, "양모펠트"),
                Category.of(4L, "요리")
        );

        given(categoryRepository.findAll()).willReturn(categories);

        // when
        ReadAllCategoriesResponse allCategoryList = categoryService.getAllCategoryList();

        // then
        assertThat(allCategoryList.getCategories().size(), is(categories.size()));
        assertThat(allCategoryList.getCategories().stream().map(ReadAllCategoryResult::getCategoryId), samePropertyValuesAs(categories.stream().map(Category::getId)));
    }

}
