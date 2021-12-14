package com.dayz.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("AddressController 통합 테스트")
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
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

        addressRepository.saveAll(addresses);
    }

    @Test
    @DisplayName("주소 목록을 조회 할 수 있다.")
    void addresses() throws Exception {
        // given
        // when // then
        mockMvc.perform(get("/api/v1/addresses"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
    }

}
