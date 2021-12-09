package com.dayz.atelier.controller;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.atelier.converter.AtelierConverter;
import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.atelier.dto.AtelierCreateRequest;
import com.dayz.atelier.service.AtelierService;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AtelierControllerTest {

    @Autowired
    AtelierRepository atelierRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AtelierService atelierService;

    @Autowired
    AtelierConverter atelierConverter;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("공방을 등록한다")
    public void createAtelier() throws Exception {
        Address address = addressRepository.save(Address.of(1L, 1L, "서울시", "송파구"));
        Permission permission_user = permissionRepository.save(Permission.of("USER"));
        Member member1 = memberRepository.save(Member.of("유저1","kakao","123432534","abc",permission_user,address));

        Atelier atelier1 = Atelier.of("개멋진공방임", address, "대륭 서초타워 2층", "겁나 멋진 공방입니다.", WorkTime.of(32400000L,68400000L),"123456789", "test", member1);
        AtelierCreateRequest atelierCreateRequest = new AtelierCreateRequest();
        atelierCreateRequest.setName(atelier1.getName());
        atelierCreateRequest.setAddress(atelier1.getAddress());
        atelierCreateRequest.setDetail(atelier1.getDetail());
        atelierCreateRequest.setIntro(atelier1.getIntro());
        atelierCreateRequest.setWorkTime(atelier1.getWorkTime());
        atelierCreateRequest.setBusinessNumber(atelier1.getBusinessNumber());
        atelierCreateRequest.setProfileImageUrl(atelier1.getProfileImageUrl());
        atelierCreateRequest.setMember(atelier1.getMember());

        mockMvc.perform(post("/api/v1/ateliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atelierCreateRequest)))
                        .andDo(print());

    }

    @Test
    @DisplayName("중복된 공방 이름으로 가입 시 예외가 발생한다")
    public void duplicatedAtelierName() throws Exception {
        Address address = addressRepository.save(Address.of(1L, 1L, "서울시", "송파구"));
        Permission permission_user = permissionRepository.save(Permission.of("USER"));
        Permission permission_user2 = permissionRepository.save(Permission.of("USER"));
        Member member1 = memberRepository.save(Member.of("유저1","kakao","123432534","abc",permission_user,address));
        Member member2 = memberRepository.save(Member.of("유저2","kakao","123432534","abc",permission_user2,address));

        Atelier atelier1 = Atelier.of("우리동네공방", address, "대륭 서초타워 1층", "멋진 공방1입니다.", WorkTime.of(32400000L,68400000L),"123456789", "test", member1);
        Atelier atelier2 = Atelier.of("우리동네공방", address, "대륭 서초타워 2층", "멋진 공방2입니다.", WorkTime.of(32400000L,68400000L),"123456789", "test", member2);

        atelierRepository.save(atelier1);

        AtelierCreateRequest atelierCreateRequest2 = new AtelierCreateRequest();
        atelierCreateRequest2.setName(atelier2.getName());
        atelierCreateRequest2.setAddress(atelier2.getAddress());
        atelierCreateRequest2.setDetail(atelier2.getDetail());
        atelierCreateRequest2.setIntro(atelier2.getIntro());
        atelierCreateRequest2.setWorkTime(atelier2.getWorkTime());
        atelierCreateRequest2.setBusinessNumber(atelier2.getBusinessNumber());
        atelierCreateRequest2.setProfileImageUrl(atelier2.getProfileImageUrl());
        atelierCreateRequest2.setMember(atelier2.getMember());

        mockMvc.perform(post("/api/v1/ateliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atelierCreateRequest2)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}