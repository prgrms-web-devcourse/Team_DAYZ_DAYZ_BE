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
public class ReadOneDayClassDetailResponse {

    private Long classId;

    private String name;

    private String intro;

    private int price;

    private double avgScore;

    private int maxPeopleNumber;

    private List<OneDayClassImageResult> images = new ArrayList<>();

    private List<CurriculumResult> curriculums = new ArrayList<>();

    private AtelierResult atelier;

    public static ReadOneDayClassDetailResponse of(Long classId,
            String name,
            String intro,
            int price,
            double avgScore,
            int maxPeopleNumber,
            List<OneDayClassImageResult> images,
            List<CurriculumResult> curriculums,
            AtelierResult atelier
    ) {

        ReadOneDayClassDetailResponse readOneDayClassDetailResponse = new ReadOneDayClassDetailResponse();
        readOneDayClassDetailResponse.setClassId(classId);
        readOneDayClassDetailResponse.setName(name);
        readOneDayClassDetailResponse.setIntro(intro);
        readOneDayClassDetailResponse.setPrice(price);
        readOneDayClassDetailResponse.setAvgScore(avgScore);
        readOneDayClassDetailResponse.setMaxPeopleNumber(maxPeopleNumber);
        readOneDayClassDetailResponse.setImages(images);
        readOneDayClassDetailResponse.setCurriculums(curriculums);
        readOneDayClassDetailResponse.setAtelier(atelier);

        return readOneDayClassDetailResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OneDayClassImageResult {

        private String imageUrl;

        private int sequence;

        public static OneDayClassImageResult of(String imageUrl, int sequence) {
            OneDayClassImageResult oneDayClassImageResult = new OneDayClassImageResult();
            oneDayClassImageResult.setImageUrl(imageUrl);
            oneDayClassImageResult.setSequence(sequence);

            return oneDayClassImageResult;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CurriculumResult {

        private Long curriculumId;

        private int step;

        private String content;

        public static CurriculumResult of(Long curriculumId, int step, String content) {
            CurriculumResult curriculumResult = new CurriculumResult();
            curriculumResult.setCurriculumId(curriculumId);
            curriculumResult.setStep(step);
            curriculumResult.setContent(content);

            return curriculumResult;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AtelierResult {

        private Long atelierId;

        private String name;

        private String address;

        private String callNumber;

        private String startTime;

        private String endTime;

        public static AtelierResult of(Long atelierId, String name, String address, String callNumber, String startTime, String endTime) {
            AtelierResult atelierResult = new AtelierResult();
            atelierResult.setAtelierId(atelierId);
            atelierResult.setName(name);
            atelierResult.setAddress(address);
            atelierResult.setCallNumber(callNumber);
            atelierResult.setStartTime(startTime);
            atelierResult.setEndTime(endTime);

            return atelierResult;
        }

    }

}
