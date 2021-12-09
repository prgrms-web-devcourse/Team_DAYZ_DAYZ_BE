package com.dayz.atelier.service;

import com.dayz.atelier.converter.AtelierConverter;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.dto.AtelierCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtelierService {

    private final AtelierRepository atelierRepository;

    private final AtelierConverter atelierConverter;

    //TODO
    @Transactional
    public Long save(AtelierCreateRequest atelierCreateRequest) {
        if (checkAtelierName(atelierCreateRequest.getName())) {
            throw new IllegalArgumentException("중복된 공방이름입니다.");
        }
        return atelierRepository
                .save(
                        atelierConverter.atelierCreateRequestToAtelier(atelierCreateRequest))
                .getId();
    }

    public boolean checkAtelierName(String name) {
        return atelierRepository.existsAtelierByName(name);
    }

}
