package com.dayz.onedayclass.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneDayClassRepository extends JpaRepository<OneDayClass, Long> {

    // TODO: findOneDayClassByCategoryId 수정이 필요
    @Query("select o from OneDayClass o inner join  o.category inner join o.atelier where o.category.id = :categoryId and o.atelier.address.cityId = :cityId and o.atelier.address.regionId = :regionId and o.useFlag = true")
    Page<OneDayClass> findOneDayClassByCategoryId(
            @Param("categoryId") Long categoryId,
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            Pageable pageRequest);

    @Query("select o from OneDayClass o "
           + "join fetch o.category "
           + "join fetch o.atelier "
           + "where o.id = :classId "
           + "and o.useFlag = true")
    Optional<OneDayClass> findOneDayClassById(@Param("classId") Long classId);

}
