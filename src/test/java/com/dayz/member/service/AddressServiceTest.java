package com.dayz.member.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.dayz.member.converter.AddressConverter;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.dto.ReadAllAddressResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("AddressService 단위테스트")
class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    @Spy
    AddressConverter addressConverter;

    @Test
    @DisplayName("모든 주소 목록 조회")
    void getAllAddresses_success() {
        // given
        List<Address> addresses = List.of(
                Address.of(1L, 1L, "서울시", "강남구"),
                Address.of(1L, 2L, "서울시", "강동구"),
                Address.of(1L, 3L, "서울시", "강북구"),
                Address.of(1L, 4L, "서울시", "관악구"),
                Address.of(1L, 5L, "서울시", "강서구"),
                Address.of(2L, 1L, "부산시", "금정구"),
                Address.of(2L, 2L, "부산시", "남구"),
                Address.of(2L, 3L, "부산시", "중구"),
                Address.of(2L, 4L, "부산시", "북구"),
                Address.of(2L, 5L, "부산시", "동구")
        );
        given(addressRepository.findAll()).willReturn(addresses);

        // when
        ReadAllAddressResponse readAllAddressResponse = addressService.getAllAddresses();

        //then
        assertThat(readAllAddressResponse, notNullValue());
        assertThat(readAllAddressResponse.getAddresses(), samePropertyValuesAs(addressConverter.convertToReadAllAddressResponse(addresses).getAddresses()));
    }

}
