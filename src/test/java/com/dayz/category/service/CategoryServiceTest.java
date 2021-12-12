package com.dayz.category.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.BDDMockito.given;

import com.dayz.category.converter.CategoryConverter;
import com.dayz.category.domain.Category;
import com.dayz.category.domain.CategoryRepository;
import com.dayz.category.dto.ReadAllCategoriesResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("CategoryService 단위테스트")
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private CategoryConverter categoryConverter;

    @Test
    @DisplayName("모든 카테고리 목록을 조회 할 수 있다.")
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
        assertThat(allCategoryList.getCategories().stream().map(ReadAllCategoriesResponse.CategoryResult::getCategoryId), samePropertyValuesAs(categories.stream().map(Category::getId)));
    }

}
