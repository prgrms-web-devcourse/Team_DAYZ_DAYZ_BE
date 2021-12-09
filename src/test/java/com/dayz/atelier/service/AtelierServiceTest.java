package com.dayz.atelier.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.atelier.dto.AtelierCreateRequest;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AtelierServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AtelierService atelierService;

    @Autowired
    AtelierRepository atelierRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeAll
    void setUp() {
        Address address2 = addressRepository.save(Address.of(1L, 1L, "서울시", "송파구"));
        Permission permission_user2 = permissionRepository.save(Permission.of("USER"));
        Member member2 = memberRepository.save(Member.of("유저1", "kakao", "123432534", "abc", permission_user2, address2));

        atelierRepository.save(
                Atelier.of("같은이름공방", address2, "대륭 서초타워 2층", "겁나 멋진 공방입니다.", WorkTime.of(32400000L, 68400000L), "123456789",
                        "test", member2));
    }


    @Test
    @DisplayName("공방 서비스를 통해 공방을 등록한다")
    public void saveAtelier() {
        Address address = addressRepository.save(Address.of(1L, 1L, "서울시", "송파구"));
        Permission permission_user = permissionRepository.save(Permission.of("USER"));
        Member member1 = memberRepository.save(Member.of("유저1", "kakao", "123432534", "abc", permission_user, address));

        Atelier atelier1 = Atelier.of("내가짱공방", address, "대륭 서초타워 2층", "겁나 멋진 공방입니다.", WorkTime.of(32400000L, 68400000L), "123456789", "test",
                member1);

        AtelierCreateRequest atelierCreateRequest = new AtelierCreateRequest();
        atelierCreateRequest.setName(atelier1.getName());
        atelierCreateRequest.setAddress(atelier1.getAddress());
        atelierCreateRequest.setDetail(atelier1.getDetail());
        atelierCreateRequest.setIntro(atelier1.getIntro());
        atelierCreateRequest.setWorkTime(atelier1.getWorkTime());
        atelierCreateRequest.setBusinessNumber(atelier1.getBusinessNumber());
        atelierCreateRequest.setProfileImageUrl(atelier1.getProfileImageUrl());
        atelierCreateRequest.setMember(atelier1.getMember());

        atelierService.save(atelierCreateRequest);

        List<Atelier> all = atelierRepository.findAll();
        assertThat(all.get(1).getName(), is("내가짱공방"));
    }

    @Test
    @DisplayName("중복된 공방이름을 등록할 수 없다")
    public void duplicationName() {
        assertThat(atelierService.checkAtelierName("같은이름공방"), is(true));
    }

}