package com.dayz.follow.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.follow.domain.Follow;
import com.dayz.follow.domain.FollowRepository;
import com.dayz.follow.dto.FollowRequest;
import com.dayz.follow.service.FollowService;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FollowControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AtelierRepository atelierRepository;

    @Autowired
    FollowService followService;

    @BeforeEach
    void setUp() {
        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Member member = Member.of("나는야카카오인증자", "kakao", "123456", "url", permission, address);
        memberRepository.save(member);

        WorkTime workTime = WorkTime.of(202002020L, 2020202020L);
        Atelier atelier = Atelier.of("atelier", address, "100", "intro Test", workTime, "123-123-123", memberRepository.findAll().get(0));
        atelierRepository.save(atelier);
    }

    @Test
    @DisplayName("멤버가 공방을 팔로우 할 수 있다")
    public void followingTest() throws Exception {
        List<Member> memberAll = memberRepository.findAll();
        List<Atelier> atelierAll = atelierRepository.findAll();

        FollowRequest fr = FollowRequest.of(memberAll.get(0).getId(), atelierAll.get(0).getId());

        mockMvc.perform(post("/api/v1/follows")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(fr)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("팔로우를 취소할 수 있다")
    public void unFollowingTest() throws Exception {
        List<Member> memberAll = memberRepository.findAll();
        List<Atelier> atelierAll = atelierRepository.findAll();

        followService.following(memberAll.get(0).getId(), atelierAll.get(0).getId());

        FollowRequest fr = FollowRequest.of(memberAll.get(0).getId(), atelierAll.get(0).getId());

        mockMvc.perform(delete("/api/v1/follows")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(fr)))
                .andDo(print());

        List<Follow> all2 = followRepository.findAll();
        assertThat(all2.size(), is(0));
    }
}