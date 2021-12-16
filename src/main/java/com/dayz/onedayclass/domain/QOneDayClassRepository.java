package com.dayz.onedayclass.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface QOneDayClassRepository {

    Page<OneDayClass> findOneDayClassByCategoryId(
            Long categoryId,
            Long cityId,
            Long regionId,
            Pageable pageRequest);

}
