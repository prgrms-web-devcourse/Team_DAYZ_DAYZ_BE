package com.dayz.atelier.domain;

import com.dayz.atelier.dto.SearchAtelierResponse;
import com.dayz.onedayclass.domain.OneDayClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface QAtelierRepository {

    Page<Atelier> findAteliersByAddress(
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            Pageable pageRequest
    );

    Page<SearchAtelierResponse> searchAteliers(Long cityId, Long regionId, String keyWord,
        Pageable pageable);
}
