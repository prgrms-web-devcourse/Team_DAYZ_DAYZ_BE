package com.dayz.atelier.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchAtelierResponse {

    private Long atelierId;

    private String name;

    private String intro;

    private String imageUrl;

    @QueryProjection
    public SearchAtelierResponse(Long atelierId, String name, String intro, String imageUrl) {
        this.atelierId = atelierId;
        this.name = name;
        this.intro = intro;
        this.imageUrl = imageUrl;
    }
}
