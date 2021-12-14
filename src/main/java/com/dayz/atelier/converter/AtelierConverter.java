package com.dayz.atelier.converter;

import com.dayz.atelier.dto.SaveAtelierResponse;
import org.springframework.stereotype.Component;

@Component
public class AtelierConverter {

    public SaveAtelierResponse convertToSaveAtelierResponse(Long atelierId, String token) {
        return SaveAtelierResponse.of(atelierId, token);
    }

}
