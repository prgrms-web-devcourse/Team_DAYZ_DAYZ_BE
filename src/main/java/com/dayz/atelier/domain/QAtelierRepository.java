package com.dayz.atelier.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface QAtelierRepository {

    Page<Atelier> findAteliersByAddress(
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            Pageable pageRequest
    );

}
