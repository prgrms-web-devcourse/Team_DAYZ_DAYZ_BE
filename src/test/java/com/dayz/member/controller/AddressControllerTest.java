package com.dayz.member.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.common.dto.ApiResponse;
import com.dayz.member.converter.AddressConverter;
import com.dayz.member.domain.Address;
import com.dayz.member.dto.ReadAllAddressResponse;
import com.dayz.member.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("AddressController 단위 테스트")
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddressConverter addressConverter;

    @Mock
    private AddressService addressService;

    @Test
    @DisplayName("주소 목록을 조회 할 수 있다.")
    void addresses() throws Exception {
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
        ReadAllAddressResponse allAddressResponse = addressConverter.convertToReadAllAddressResponse(addresses);
        given(addressService.getAllAddresses()).willReturn(allAddressResponse);

        // when // then
        mockMvc.perform(get("/api/v1/addresses"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(ApiResponse.<ReadAllAddressResponse>ok(allAddressResponse))))
                .andExpect(status().isOk());
    }

}
