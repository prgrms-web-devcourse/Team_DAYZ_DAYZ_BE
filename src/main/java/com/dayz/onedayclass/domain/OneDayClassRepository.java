package com.dayz.onedayclass.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneDayClassRepository extends JpaRepository<OneDayClass, Long>, QOneDayClassRepository {

    String SQL_findPopularOneDayClassIds =
            "SELECT o.onedayclass_id AS 'oneDayClassId'"
            + " FROM onedayclass o "
            + " INNER JOIN onedayclass_time ot"
            + "     ON o.onedayclass_id = ot.onedayclass_id"
            + " INNER JOIN reservation r"
            + "     ON ot.onedayclass_time_id = r.onedayclass_time_id"
            + " INNER JOIN atelier a"
            + "     ON o.atelier_id  = a.atelier_id"
            + " INNER JOIN address aa"
            + "     ON a.address_id = aa.address_id"
            + " WHERE ot.class_date >= :startDate"
            + "     AND ot.class_date <= :endDate"
            + "     AND aa.city_id = :cityId"
            + "     AND aa.region_id = :regionId"
            + " GROUP BY o.onedayclass_id"
            + " ORDER BY count(r.reservation_id) DESC"
            + " LIMIT :size";

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

    @Query("select o from OneDayClass o"
           + " join fetch o.atelier"
           + " join fetch o.category"
           + " where o.id in :ids")
    List<OneDayClass> findOneDayClassesByIds(@Param("ids") List<Long> ids);

    @Query(value = SQL_findPopularOneDayClassIds, nativeQuery = true)
    List<Long> findPopularOneDayClassIds(
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("size") int size);

}
