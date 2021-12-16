package com.dayz.onedayclass.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadOneDayClassByAtelierResult {

    private Long oneDayClassId;

    private String name;

    private String imageUrl;

    public static ReadOneDayClassByAtelierResult of(Long oneDayClassId, String name, String imageUrl) {
        ReadOneDayClassByAtelierResult readOneDayClassByAtelierResult = new ReadOneDayClassByAtelierResult();
        readOneDayClassByAtelierResult.setOneDayClassId(oneDayClassId);
        readOneDayClassByAtelierResult.setName(name);
        readOneDayClassByAtelierResult.setImageUrl(imageUrl);

        return readOneDayClassByAtelierResult;
    }

}
