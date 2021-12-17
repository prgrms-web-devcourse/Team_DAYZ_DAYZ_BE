package com.dayz.onedayclass.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class SearchOneDayClassResponse {

    private Long classId;

    private String name;

    private String intro;

    private String imageUrl;

    public static SearchOneDayClassResponse of(Long classId, String name, String intro,
        String imageUrl) {
        SearchOneDayClassResponse searchOneDayClassResponse = new SearchOneDayClassResponse();
        searchOneDayClassResponse.setClassId(classId);
        searchOneDayClassResponse.setName(name);
        searchOneDayClassResponse.setIntro(intro);
        searchOneDayClassResponse.setImageUrl(imageUrl);

        return searchOneDayClassResponse;
    }
}
