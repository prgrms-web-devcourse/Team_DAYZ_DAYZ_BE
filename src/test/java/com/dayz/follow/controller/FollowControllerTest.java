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
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomSort;
import com.dayz.follow.domain.Follow;
import com.dayz.follow.domain.FollowRepository;
import com.dayz.follow.dto.FollowRequest;
import com.dayz.follow.service.FollowService;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @BeforeEach
    void setUp() {
        Permission permission1 = Permission.of("USER");
        Permission permission2 = Permission.of("ATELIER");
        permissionRepository.save(permission1);
        permissionRepository.save(permission2);

        Address address1 = Address.of(1L, 1L, "서울시", "송파구");
        Address address2 = Address.of(1L, 1L, "서울시", "송파구");
        addressRepository.save(address1);
        addressRepository.save(address2);

//        Member member1 = Member.of("나는야이용자", "kakao", "123456", "url", Permission.of("USER"));
//        memberRepository.save(member1);
//        Member member2 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
//        memberRepository.save(member2);
//        Member member3 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
//        memberRepository.save(member3);
//        Member member4 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
//        memberRepository.save(member4);
//        Member member5 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
//        memberRepository.save(member5);
//        Member member6 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
//        memberRepository.save(member6);

        Member member11 = Member.of("나는야이용자", "kakao", "123456", "url", Permission.of("USER"));
        Atelier atelier1 = Atelier.of("atelier", "100", "intro Test", WorkTime.of(202002020L, 2020202020L), "123-123-123", member11);
        atelierRepository.save(atelier1);
        Member member22 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
        Atelier atelier2 = Atelier.of("atelier", "100", "intro Test", WorkTime.of(202002020L, 2020202020L), "123-123-123", member22);
        atelierRepository.save(atelier2);
        Member member33 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
        Atelier atelier3 = Atelier.of("atelier", "100", "intro Test", WorkTime.of(202002020L, 2020202020L), "123-123-123", member33);
        atelierRepository.save(atelier3);
        Member member44 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
        Atelier atelier4 = Atelier.of("atelier", "100", "intro Test", WorkTime.of(202002020L, 2020202020L), "123-123-123", member44);
        atelierRepository.save(atelier4);
        Member member55 = Member.of("나는야공방주인", "kakao", "34536", "url", Permission.of("ATELIER"));
        Atelier atelier5 = Atelier.of("atelier", "100", "intro Test", WorkTime.of(202002020L, 2020202020L), "123-123-123", member55);
        atelierRepository.save(atelier5);

        List<Member> memberList = memberRepository.findAllByUseFlagIsTrue();
        Long id = memberList.get(0).getId();
        List<Atelier> atelierAll = atelierRepository.findAll();

        followService.followingUnfollowing(id, atelierAll.get(0).getId());
        followService.followingUnfollowing(id, atelierAll.get(1).getId());
        followService.followingUnfollowing(id, atelierAll.get(2).getId());
        followService.followingUnfollowing(id, atelierAll.get(3).getId());
        followService.followingUnfollowing(id, atelierAll.get(4).getId());

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

        followService.followingUnfollowing(memberAll.get(0).getId(), atelierAll.get(0).getId());

        FollowRequest fr = FollowRequest.of(memberAll.get(0).getId(), atelierAll.get(0).getId());

        mockMvc.perform(post("/api/v1/follows")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(fr)))
                .andDo(print());

        assertThat(followRepository.findAll().get(0).isUseFlag(), is(false));
    }

    @Test
    @DisplayName("팔로잉 목록을 조회할 수 있다")
    @Transactional
    public void readAllFollowing() throws Exception {
        List<Member> memberAll = memberRepository.findAll();

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/follows/{memberId}", memberAll.get(0).getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CustomPageRequest.of(0, 10, CustomSort.of("createdAt", "ASC")))))
                .andDo(print());

    }

}