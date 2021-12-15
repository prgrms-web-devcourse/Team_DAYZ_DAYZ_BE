package com.dayz.atelier.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtelierRepository extends JpaRepository<Atelier, Long> {

//    @Query("select a from Atelier a "
//           + "join fetch a.member "
//           + "join fetch a.address "
//           + "where a.useFlag = true")
//    Page<Atelier> findAteliers(Pageable pageRequest);

//    @Query("select a from Atelier a "
//           + "join fetch a.member "
//           + "join fetch a.address aa "
//           + "where aa.cityId = :cityId "
//           + "and aa.regionId = :regionId "
//           + "and a.useFlag = true"
//    )
    // TODO : AtelierRepository 최적화가 필요
    @Query("select a from Atelier a "
           + "inner join a.member "
           + "inner join a.address aa "
           + "where aa.cityId = :cityId "
           + "and aa.regionId = :regionId "
           + "and a.useFlag = true"
    )
    Page<Atelier> findAteliersByAddress(
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            Pageable pageRequest
    );

}
