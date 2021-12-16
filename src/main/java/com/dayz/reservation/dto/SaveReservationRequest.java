package com.dayz.reservation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class SaveReservationRequest {

    @NotNull
    private Long classTimeId;

    @Min(1)
    private int price;

    @Min(1)
    private int peopleNumber;

}
