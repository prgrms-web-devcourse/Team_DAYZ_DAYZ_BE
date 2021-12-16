package com.dayz.onedayclass.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.category.domain.Category;
import com.dayz.category.domain.CategoryRepository;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomSort;
import com.dayz.common.jwt.Jwt;
import com.dayz.common.jwt.Jwt.Claims;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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
@DisplayName("OneDayClassController 통합 테스트")
class OneDayClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AtelierRepository atelierRepository;

    @Autowired
    private OneDayClassRepository oneDayClassRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Jwt jwt;

    String token = null;

    @BeforeEach
    void setUp() {
        Permission permission = Permission.of("ROLE_ATELIER");
        permissionRepository.save(permission);

        Category category = Category.of("도자기");
        categoryRepository.save(category);

        Address address = Address.of(1L, 1L, "서울시", "강남구");
        addressRepository.save(address);

        Member member = Member.of("김유저", "kakao", "2019948491", null, permission, address);
        memberRepository.save(member);

        Atelier atelier = Atelier.of("개멋진공방", address, "대륭 서초타워 2층", "겁나 멋진 공방입니다.", WorkTime.of(32400000L, 68400000L), "123456789", member);
        atelierRepository.save(atelier);

        List<OneDayClass> oneDayClasses = List.of(
                OneDayClass.of("도자기 만들기 클래스 1", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>(), new ArrayList<>()),
                OneDayClass.of("도자기 만들기 클래스 2", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>(), new ArrayList<>()),
                OneDayClass.of("도자기 만들기 클래스 3", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>(), new ArrayList<>()),
                OneDayClass.of("도자기 만들기 클래스 4", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>(), new ArrayList<>()),
                OneDayClass.of("도자기 만들기 클래스 5", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>(), new ArrayList<>())
        );
        oneDayClassRepository.saveAll(oneDayClasses);

        token = getToken(member);
    }

    @Test
    @DisplayName("카테고리 별 원데이 클래스 목록 조회 - 성공")
    void findOneDayClassesByCategory_success() throws Exception {

        Long categoryId = 1L;

        CustomPageRequest pageRequest = CustomPageRequest.of(
                0,
                5,
                CustomSort.of("createdAt", "DESC"));

        // when // then
        mockMvc.perform(get("/api/v1/classes/categories/" + categoryId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(pageRequest))
                .header("Authorization", token))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String getToken(Member member) {
        return jwt.sign(Claims.from(member.getId(), member.getProviderId(), member.getUsername(), new String[]{member.getPermission().getName()}));
    }

    @AfterEach
    void tearDown() {
        oneDayClassRepository.deleteAll();
        atelierRepository.deleteAll();
        memberRepository.deleteAll();
        addressRepository.deleteAll();
        categoryRepository.deleteAll();
        permissionRepository.deleteAll();
    }

}
