package com.dayz.atelier.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveAtelierRequest {

    @NotBlank(message = "name is not blank")
    private String name;

    @NotBlank(message = "businessNumber is not blank")
    private String businessNumber;

    @NotBlank(message = "address is not null")
    private AddressResult address;

    private String intro;

    @NotBlank(message = "workStartTime is not blank")
    private String workStartTime;

    @NotBlank(message = "workEndTime is not blank")
    private String workEndTime;


    public static SaveAtelierRequest of(String name, String businessNumber, AddressResult address, String workStartTime, String workEndTime) {
        SaveAtelierRequest saveAtelierRequest = new SaveAtelierRequest();
        saveAtelierRequest.setName(name);
        saveAtelierRequest.setBusinessNumber(businessNumber);
        saveAtelierRequest.setAddress(address);
        saveAtelierRequest.setWorkStartTime(workStartTime);
        saveAtelierRequest.setWorkEndTime(workEndTime);

        return saveAtelierRequest;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddressResult {

        @NotNull(message = "cityId is not null")
        private Long cityId;

        @NotNull(message = "regionId is not null")
        private Long regionId;

        private String detail;

        public static AddressResult of(Long cityId, Long regionId, String detail) {
            AddressResult addressResult = new AddressResult();
            addressResult.setCityId(cityId);
            addressResult.setRegionId(regionId);
            addressResult.setDetail(detail);

            return addressResult;
        }
    }

}
