package com.dayz.onedayclass.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveOneDayClassRequest {

    @NotNull(message = "atelierId is not null")
    private Long atelierId;

    @NotBlank(message = "name is not blank")
    private String name;

    private String intro;

    @NotNull(message = "categoryId is not null")
    private Long categoryId;

    @Positive(message = "maxPeopleNumber must be positive")
    @Min(1)
    private int maxPeopleNumber;

    @Positive(message = "price must be positive")
    private int price;

    @NotBlank(message = "requiredTime is not blank")
    @Pattern(regexp = "^(([0-1]{1}[0-9]{1})|([2]{1}[0-3]{1})):(([0-5]{1}[0-9]{1}))$", message = "requiredTime must be HH:mm format")
    private String requiredTime;

    @Valid
    private List<CurriculumRequest> curriculums = new ArrayList<>();

    @Valid
    private List<OneDayClassImageRequest> images = new ArrayList<>();

    @Valid
    private List<OneDayClassTimeRequest> oneDayClassTimes = new ArrayList<>();

    public static SaveOneDayClassRequest of(
            Long atelierId,
            String name,
            String intro,
            Long categoryId,
            int maxPeopleNumber,
            int price,
            String requiredTime,
            List<CurriculumRequest> curriculums,
            List<OneDayClassImageRequest> images,
            List<OneDayClassTimeRequest> oneDayClassTimes) {
        SaveOneDayClassRequest saveOneDayClassRequest = new SaveOneDayClassRequest();
        saveOneDayClassRequest.setAtelierId(atelierId);
        saveOneDayClassRequest.setName(name);
        saveOneDayClassRequest.setIntro(intro);
        saveOneDayClassRequest.setCategoryId(categoryId);
        saveOneDayClassRequest.setMaxPeopleNumber(maxPeopleNumber);
        saveOneDayClassRequest.setPrice(price);
        saveOneDayClassRequest.setRequiredTime(requiredTime);
        saveOneDayClassRequest.setCurriculums(curriculums);
        saveOneDayClassRequest.setImages(images);
        saveOneDayClassRequest.setOneDayClassTimes(oneDayClassTimes);

        return saveOneDayClassRequest;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CurriculumRequest {

        @Positive(message = "step must be positive")
        @Min(1)
        private int step;

        private String content;

        public static CurriculumRequest of(int step, String content) {
            CurriculumRequest curriculumRequest = new CurriculumRequest();
            curriculumRequest.setStep(step);
            curriculumRequest.setContent(content);

            return curriculumRequest;
        }

    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OneDayClassImageRequest {

        @NotBlank(message = "imageUrl is not blank")
        private String imageUrl;

        @Positive(message = "sequence must be positive")
        @Min(1)
        private int sequence;

        public static OneDayClassImageRequest of(String imageUrl, int sequence) {
            OneDayClassImageRequest oneDayClassImageRequest = new OneDayClassImageRequest();
            oneDayClassImageRequest.setImageUrl(imageUrl);
            oneDayClassImageRequest.setSequence(sequence);

            return oneDayClassImageRequest;
        }
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OneDayClassTimeRequest {

        @NotBlank(message = "date is not blank")
        @Pattern(regexp = "^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)$", message = "date must be YYYY-MM-DD format")
        private String date;

        @NotBlank(message = "startTime is not blank")
        @Pattern(regexp = "^(([0-1]{1}[0-9]{1})|([2]{1}[0-3]{1})):(([0-5]{1}[0-9]{1}))$", message = "workStartTime must be HH:mm format")
        private String startTime;

        @NotBlank(message = "endTime is not blank")
        @Pattern(regexp = "^(([0-1]{1}[0-9]{1})|([2]{1}[0-3]{1})):(([0-5]{1}[0-9]{1}))$", message = "workStartTime must be HH:mm format")
        private String endTime;

        public static OneDayClassTimeRequest of(String date, String startTime, String endTime) {
            OneDayClassTimeRequest oneDayClassTimeRequest = new OneDayClassTimeRequest();
            oneDayClassTimeRequest.setDate(date);
            oneDayClassTimeRequest.setStartTime(startTime);
            oneDayClassTimeRequest.setEndTime(endTime);

            return oneDayClassTimeRequest;
        }

    }

}
