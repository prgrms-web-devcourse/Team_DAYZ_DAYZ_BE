package com.dayz.atelier.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveAtelierResponse {

    private Long atelierId;

    private String token;

    public static SaveAtelierResponse of(Long atelierId, String token) {
        SaveAtelierResponse saveAtelierResponse = new SaveAtelierResponse();
        saveAtelierResponse.setAtelierId(atelierId);
        saveAtelierResponse.setToken(token);

        return saveAtelierResponse;
    }

}
