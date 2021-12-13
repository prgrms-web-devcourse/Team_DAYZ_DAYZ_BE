package com.dayz.member.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditMemberAddressRequest {

    @NotNull(message = "cityId is not null")
    @Positive(message = "cityId must be positive")
    private Long cityId;

    @NotNull(message = "regionId is not null")
    @Positive(message = "regionId must be positive")
    private Long regionId;

    public static EditMemberAddressRequest of(Long cityId, Long regionId) {
        EditMemberAddressRequest editMemberAddressRequest = new EditMemberAddressRequest();
        editMemberAddressRequest.setCityId(cityId);
        editMemberAddressRequest.setRegionId(regionId);

        return editMemberAddressRequest;
    }

}
