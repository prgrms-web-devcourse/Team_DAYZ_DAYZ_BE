package com.dayz.atelier.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtelierRepository extends JpaRepository<Atelier, Long>, QAtelierRepository {

    @Query("select a from Atelier a "
           + "join fetch a.address aa "
           + "join fetch a.member m "
           + "where a.id = :atelierId "
           + "and a.useFlag = true")
    Optional<Atelier> findById(@Param("atelierId") Long atelierId);

}
