package com.dayz.atelier.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadAteliersResult {

    private Long atelierId;

    private String name;

    private String imageUrl;

    private String intro;

    public static ReadAteliersResult of(Long atelierId, String name, String imageUrl, String intro) {
        ReadAteliersResult readAteliersResult = new ReadAteliersResult();
        readAteliersResult.setAtelierId(atelierId);
        readAteliersResult.setName(name);
        readAteliersResult.setIntro(intro);
        readAteliersResult.setImageUrl(imageUrl);

        return readAteliersResult;
    }

}
