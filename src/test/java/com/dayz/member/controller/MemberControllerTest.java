package com.dayz.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.common.jwt.Jwt;
import com.dayz.common.jwt.Jwt.Claims;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import com.dayz.member.dto.EditMemberAddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@DisplayName("MemberController 통합 테스트")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Jwt jwt;

    String token = null;

    @BeforeEach
    void setUp() {
        List<Permission> permissions = List.of(
                Permission.of("ROLE_USER"),
                Permission.of("ROLE_ATELIER")
        );
        permissionRepository.saveAll(permissions);

        List<Address> addresses = List.of(
                Address.of(1L, 1L, "서울시", "강남구"),
                Address.of(1L, 2L, "서울시", "강동구")
        );
        addressRepository.saveAll(addresses);

        List<Member> members = List.of(
                Member.of("김유저", "kakao", "2019948491", null, permissions.get(0), addresses.get(0)),
                Member.of("김공방", "kakao", "2019948492", null, permissions.get(1), addresses.get(1))
        );
        List<Member> savedMembers = memberRepository.saveAll(members);

        Member member = members.get(0);
        token = getToken(member);
    }

    @Test
    @DisplayName("관심지역 변경 - 성공")
//    @WithMockUser(roles = {"USER"})
    void editMemberAddress_success() throws Exception {
        // given
        Long cityIdForEdit = 1L;
        Long regionIdForEdit = 2L;

        EditMemberAddressRequest request = EditMemberAddressRequest.of(cityIdForEdit, regionIdForEdit);

        // when // then
        mockMvc.perform(post("/api/v1/members/address")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", memberRepository.findById(1L)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String getToken(Member member) {
        return jwt.sign(Claims.from(member.getId(), member.getProviderId(), member.getUsername(), new String[]{member.getPermission().getName()}));
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
        permissionRepository.deleteAll();
        addressRepository.deleteAll();
    }

}
