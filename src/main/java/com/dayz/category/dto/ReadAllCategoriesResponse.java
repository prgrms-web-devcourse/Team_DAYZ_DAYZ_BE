package com.dayz.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadAllCategoriesResponse {

    private List<ReadAllCategoryResult> categories = new ArrayList<>();

    public static ReadAllCategoriesResponse of(List<ReadAllCategoryResult> categories) {
        ReadAllCategoriesResponse readAllCategoriesResponse = new ReadAllCategoriesResponse();
        readAllCategoriesResponse.setCategories(categories);

        return readAllCategoriesResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReadAllCategoryResult {

        private Long categoryId;

        private String name;

        public static ReadAllCategoryResult of(Long categoryId, String name) {
            ReadAllCategoryResult readAllCategoryResult = new ReadAllCategoryResult();
            readAllCategoryResult.setCategoryId(categoryId);
            readAllCategoryResult.setName(name);

            return readAllCategoryResult;
        }

    }

}


