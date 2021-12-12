package com.dayz.onedayclass.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadOneDayClassesByCategoryResult {

    private Long oneDayClassId;

    private String name;

    private String intro;

    private String imageUrl;

    public static ReadOneDayClassesByCategoryResult of(Long classId, String name, String intro, String imageUrl) {
        ReadOneDayClassesByCategoryResult readOneDayClassesByCategoryResult = new ReadOneDayClassesByCategoryResult();
        readOneDayClassesByCategoryResult.setOneDayClassId(classId);
        readOneDayClassesByCategoryResult.setName(name);
        readOneDayClassesByCategoryResult.setIntro(intro);
        readOneDayClassesByCategoryResult.setImageUrl(imageUrl);

        return readOneDayClassesByCategoryResult;
    }

}
