package com.dayz.atelier.converter;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.dto.AtelierCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class AtelierConverter {

    public Atelier atelierCreateRequestToAtelier (AtelierCreateRequest request) {
        return Atelier.of(request.getName(), request.getAddress(), request.getDetail(), request.getIntro(), request.getWorkTime(),
                request.getProfileImageUrl(), request.getBusinessNumber(), request.getMember());
    }

}
