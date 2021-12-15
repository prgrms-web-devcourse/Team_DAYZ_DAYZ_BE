package com.dayz.atelier.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadAtelierDetailResponse {

    private Long atelierId;

    private String name;

    private String intro;

    private String address;

    private String callNumber;

    private String startTime;

    private String endTime;

    public static ReadAtelierDetailResponse of(Long atelierId,
            String name,
            String intro,
            String address,
            String callNumber,
            String startTime,
            String endTime) {
        ReadAtelierDetailResponse readAtelierDetailResponse = new ReadAtelierDetailResponse();
        readAtelierDetailResponse.setAtelierId(atelierId);
        readAtelierDetailResponse.setName(name);
        readAtelierDetailResponse.setIntro(intro);
        readAtelierDetailResponse.setAddress(address);
        readAtelierDetailResponse.setCallNumber(callNumber);
        readAtelierDetailResponse.setStartTime(startTime);
        readAtelierDetailResponse.setEndTime(endTime);

        return readAtelierDetailResponse;
    }

}
