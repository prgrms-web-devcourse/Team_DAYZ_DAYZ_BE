package com.dayz.onedayclass.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadPopularOneDayClassesResponse {

    private List<OneDayClassResult> oneDayClasses = new ArrayList<>();

    public static ReadPopularOneDayClassesResponse of(List<OneDayClassResult> oneDayClasses) {
        ReadPopularOneDayClassesResponse readPopularOneDayClassesResponse = new ReadPopularOneDayClassesResponse();
        readPopularOneDayClassesResponse.setOneDayClasses(oneDayClasses);

        return readPopularOneDayClassesResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OneDayClassResult {

        private Long classId;

        private String name;

        private String intro;

        private String imageUrl;

        public static OneDayClassResult of(Long oneDayClassId, String name, String intro, String imageUrl) {
            OneDayClassResult oneDayClassResult = new OneDayClassResult();
            oneDayClassResult.setClassId(oneDayClassId);
            oneDayClassResult.setName(name);
            oneDayClassResult.setIntro(intro);
            oneDayClassResult.setImageUrl(imageUrl);

            return oneDayClassResult;
        }

    }

}
