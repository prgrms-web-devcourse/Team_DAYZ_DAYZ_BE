package com.dayz.member.service;

import com.dayz.member.converter.AddressConverter;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.dto.ReadAllAddressResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressConverter addressConverter;

    public ReadAllAddressResponse getAllAddresses() {
        List<Address> foundAllAddresses = addressRepository.findAll();
        return addressConverter.convertToReadAllAddressResponse(foundAllAddresses);
    }

}
