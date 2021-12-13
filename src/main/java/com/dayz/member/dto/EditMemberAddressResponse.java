package com.dayz.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditMemberAddressResponse {

    private Long cityId;

    private String cityName;

    private Long regionId;

    private String regionName;

    public static EditMemberAddressResponse of(Long cityId, String cityName, Long regionId, String regionName) {
        EditMemberAddressResponse editMemberAddressResponse = new EditMemberAddressResponse();
        editMemberAddressResponse.setCityId(cityId);
        editMemberAddressResponse.setCityName(cityName);
        editMemberAddressResponse.setRegionId(regionId);
        editMemberAddressResponse.setRegionName(regionName);

        return editMemberAddressResponse;
    }

}
