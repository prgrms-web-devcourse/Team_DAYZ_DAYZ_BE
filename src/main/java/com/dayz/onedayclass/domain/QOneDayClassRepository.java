package com.dayz.onedayclass.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QOneDayClassRepository {

    Page<OneDayClass> findOneDayClassByCategoryId(
        Long categoryId,
        Long cityId,
        Long regionId,
        Pageable pageRequest);

    Page<OneDayClass> searchOneDayClass(Long cityId, Long regionId, String keyWord,
        Pageable pageable);
}
