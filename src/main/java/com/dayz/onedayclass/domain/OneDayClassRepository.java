package com.dayz.onedayclass.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneDayClassRepository extends JpaRepository<OneDayClass, Long>, QOneDayClassRepository {

    @Query("select o from OneDayClass o "
           + "join fetch o.category "
           + "join fetch o.atelier "
           + "where o.id = :classId "
           + "and o.useFlag = true")
    Optional<OneDayClass> findOneDayClassById(@Param("classId") Long classId);

    @Query(
            "select o from OneDayClass o "
           + "where o.atelier.id = :atelierId "
           + "and o.useFlag = true"
    )
    Page<OneDayClass> findOneDayClassByAtelierId(@Param("atelierId") Long atelierId, Pageable pageRequest);

}
