package com.dayz.onedayclass.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OneDayClassRepository extends JpaRepository<OneDayClass, Long> {

    @Query("select o from OneDayClass o where o.id = :onDayClassId and o.useFlag = true")
    Optional<OneDayClass> findOneDayClassByIdAAndUseFlagIsTrue(@Param("oneDayClassId") Long oneDayClassId);

}
