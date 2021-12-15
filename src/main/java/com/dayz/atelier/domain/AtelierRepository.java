package com.dayz.atelier.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtelierRepository extends JpaRepository<Atelier, Long> {

    @Query("select a from Atelier a where a.id = :atelierId and a.useFlag = true")
    Optional<Atelier> findAtelierByIdAAndUseFlagIsTrue(@Param("atelierId") Long atelierId);

}
