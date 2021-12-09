package com.dayz.member.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadAllAddressResponse {

    private List<AddressByCityResult> addresses = new ArrayList<>();

    public static ReadAllAddressResponse of() {
        return new ReadAllAddressResponse();
    }

    public void addAddressByCityResult(AddressByCityResult addressByCityResult) {
        this.addresses.add(addressByCityResult);
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddressByCityResult {

        private Long cityId;

        private String cityName;

        private List<RegionByCityResult> regions = new ArrayList<>();

        public static AddressByCityResult of(Long cityId, String cityName,
                List<RegionByCityResult> regions) {
            AddressByCityResult addressByCityResult = new AddressByCityResult();
            addressByCityResult.setCityId(cityId);
            addressByCityResult.setCityName(cityName);
            addressByCityResult.setRegions(regions);

            return addressByCityResult;
        }

        @Getter
        @Setter(AccessLevel.PRIVATE)
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class RegionByCityResult {

            private Long regionId;

            private String regionName;

            public static RegionByCityResult of(Long regionId, String regionName) {
                RegionByCityResult regionByCityResult = new RegionByCityResult();
                regionByCityResult.setRegionId(regionId);
                regionByCityResult.setRegionName(regionName);

                return regionByCityResult;
            }

        }

    }

}
