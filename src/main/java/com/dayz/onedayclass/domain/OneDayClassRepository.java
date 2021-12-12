package com.dayz.onedayclass.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneDayClassRepository extends JpaRepository<OneDayClass, Long> {

    @Query("select o from OneDayClass o where o.category.id = :categoryId and o.atelier.address.cityId = :cityId and o.atelier.address.regionId = :regionId and o.useFlag = true")
    Page<OneDayClass> findOneDayClassByCategoryId(
            @Param("categoryId") Long categoryId,
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            Pageable pageRequest);

}
