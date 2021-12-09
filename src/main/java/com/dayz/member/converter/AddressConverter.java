package com.dayz.member.converter;

import com.dayz.member.domain.Address;
import com.dayz.member.dto.ReadAllAddressResponse;
import com.dayz.member.dto.ReadAllAddressResponse.AddressByCityResult;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public ReadAllAddressResponse convertToReadAllAddressResponse(List<Address> addresses) {

        ReadAllAddressResponse readAllAddressResponse = ReadAllAddressResponse.of();

        ConcurrentMap<Long, List<Address>> collect = addresses.stream()
                .collect(Collectors.groupingByConcurrent(Address::getCityId));

        collect.forEach((cityId, addressByCityList) -> {
            AddressByCityResult addressByCityResult = convertToAddressByCityResult(cityId, addressByCityList.get(0).getCityName(), addressByCityList);
            readAllAddressResponse.addAddressByCityResult(addressByCityResult);
        });

        return readAllAddressResponse;

    }

    public ReadAllAddressResponse.AddressByCityResult convertToAddressByCityResult(Long cityId, String cityName, List<Address> addresses) {
        return ReadAllAddressResponse.AddressByCityResult.of(
                cityId,
                cityName,
                addresses.stream()
                        .map(this::convertToRegionByCityResult)
                        .collect(Collectors.toList())
        );
    }

    public ReadAllAddressResponse.AddressByCityResult.RegionByCityResult convertToRegionByCityResult(Address addressByCityId) {
        return ReadAllAddressResponse.AddressByCityResult.RegionByCityResult.of(addressByCityId.getRegionId(), addressByCityId.getRegionName());
    }

}
