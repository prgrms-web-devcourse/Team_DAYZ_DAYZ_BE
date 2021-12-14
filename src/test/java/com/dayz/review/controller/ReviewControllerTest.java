package com.dayz.review.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomSort;
import com.dayz.common.jwt.Jwt;
import com.dayz.common.jwt.Jwt.Claims;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.PermissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private Jwt jwt;

    String token = null;

    @BeforeEach
    void setup(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//            .addFilters(new CharacterEncodingFilter("UTF-8", true))
//            .alwaysDo(print())
//            .build();
        Member member = memberRepository.findById(1L).get();
        token = getToken(member);
    }
    @Test
    @DisplayName("내가 쓴 후기 전체 조회 컨트롤러 테스트")
    void getReviews() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomPageRequest.of(0, 10, CustomSort.of("createdAt", "ASC"))))
                .header("Authorization", token))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공방 별 후기 전체 조회 컨트롤러 테스트")
    void getAtelierReviews() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/reviews/atelier/{atelierId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomPageRequest.of(0, 10, CustomSort.of("createdAt", "ASC"))))
                .header("Authorization", token))
            .andExpect(status().isOk());
    }

    private String getToken(Member member) {
        return jwt.sign(
            Claims.from(member.getId(), member.getProviderId(), member.getUsername(), new String[]{member.getPermission().getName()}));
    }

}