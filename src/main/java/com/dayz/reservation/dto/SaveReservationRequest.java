package com.dayz.reservation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SaveReservationRequest {

    @NotNull(message = "classTimeId null이 될 수 없습니다.")
    private Long classTimeId;

    @Min(value = 1,message = "price값은 1 이상이어야 합니다")
    private int price;

    @Min(value = 1,message = "peopelNumber의 값은 1이상이어야 합니다.")
    private int peopleNumber;

}
