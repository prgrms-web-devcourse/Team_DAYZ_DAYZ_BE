package com.dayz.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadMemberInfoResponse {

    private Long id;

    private String token;

    private String name;

    private String profileImageUrl;

    private Long cityId;

    private String cityName;

    private Long regionId;

    private String regionName;

    private String auth;

    private Long atelierId;

    public static ReadMemberInfoResponse of(Long id, String token, String name, String profileImageUrl, Long cityId, String cityName, Long regionId,
            String regionName, String auth, Long atelierId) {
        Assert.notNull(id,"id must not be null!");
        Assert.notNull(token,"token must not be null!");
        Assert.notNull(name,"name must not be null!");
        Assert.notNull(profileImageUrl,"profileImageUrl must not be null!");
        Assert.notNull(auth,"auth must not be null!");

        ReadMemberInfoResponse readMemberInfoResponse = new ReadMemberInfoResponse();
        readMemberInfoResponse.setId(id);
        readMemberInfoResponse.setToken(token);
        readMemberInfoResponse.setName(name);
        readMemberInfoResponse.setProfileImageUrl(profileImageUrl);
        readMemberInfoResponse.setCityId(cityId);
        readMemberInfoResponse.setCityName(cityName);
        readMemberInfoResponse.setRegionId(regionId);
        readMemberInfoResponse.setRegionName(regionName);
        readMemberInfoResponse.setAuth(auth);
        readMemberInfoResponse.setAtelierId(atelierId);

        return readMemberInfoResponse;
    }
}
