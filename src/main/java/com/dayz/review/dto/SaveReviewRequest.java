package com.dayz.review.dto;


import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class SaveReviewRequest {

    @NotNull
    private Long reservationId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Min(1)
    @Max(5)
    private int score;

    @Valid
    private List<SaveReviewImageRequest> images = new ArrayList<>();

    public static SaveReviewRequest of(Long reservationId, String title, String content, int score,
        List<SaveReviewImageRequest> images) {
        SaveReviewRequest saveReviewRequest = new SaveReviewRequest();
        saveReviewRequest.setReservationId(reservationId);
        saveReviewRequest.setTitle(title);
        saveReviewRequest.setContent(content);
        saveReviewRequest.setScore(score);
        saveReviewRequest.setImages(images);

        return saveReviewRequest;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    public static class SaveReviewImageRequest {

        @NotBlank(message = "파일 이름이 null이 될 수 없습니다.")
        private String imageUrl;

        @Min(1)
        private int sequence;

        public static SaveReviewImageRequest of(String imageUrl, int sequence) {
            SaveReviewImageRequest saveReviewImageRequest = new SaveReviewImageRequest();
            saveReviewImageRequest.setImageUrl(imageUrl);
            saveReviewImageRequest.setSequence(sequence);

            return saveReviewImageRequest;
        }
    }

}
