package com.dayz.atelier.converter;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.dto.ReadAteliersResult;
import com.dayz.atelier.dto.SaveAtelierResponse;
import com.dayz.common.util.ImageUrlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AtelierConverter {

    public SaveAtelierResponse convertToSaveAtelierResponse(Long atelierId, String token) {
        return SaveAtelierResponse.of(atelierId, token);
    }

    public ReadAteliersResult convertToReadAteliersResult(Atelier atelier) {
        return ReadAteliersResult.of(
                atelier.getId(),
                atelier.getName(),
                atelier.getMember().getProfileImageUrl(),
                atelier.getIntro()
        );
    }

}
