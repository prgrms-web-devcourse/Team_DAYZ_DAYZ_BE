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

    @NotNull(message = "리뷰 아이디 값이 null일 수 없습니다.")
    private Long reservationId;

    @NotBlank(message = "리뷰 내용은 빈킨이 될 수 없습니다.")
    private String content;

    @Min(value = 1,message = "score는 1이상이어야 합니다.")
    @Max(5)
    private int score;

    @Valid
    private List<SaveReviewImageRequest> images = new ArrayList<>();

    public static SaveReviewRequest of(Long reservationId, String content, int score,
        List<SaveReviewImageRequest> images) {
        SaveReviewRequest saveReviewRequest = new SaveReviewRequest();
        saveReviewRequest.setReservationId(reservationId);
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

        @Min(value = 1,message = "sequence는 1이상이어야 합니다.")
        private int sequence;

        public static SaveReviewImageRequest of(String imageUrl, int sequence) {
            SaveReviewImageRequest saveReviewImageRequest = new SaveReviewImageRequest();
            saveReviewImageRequest.setImageUrl(imageUrl);
            saveReviewImageRequest.setSequence(sequence);

            return saveReviewImageRequest;
        }
    }

}
