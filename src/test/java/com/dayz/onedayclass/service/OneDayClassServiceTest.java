package com.dayz.onedayclass.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.BDDMockito.given;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.category.domain.Category;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.dto.CustomSort;
import com.dayz.common.util.ImageUrlUtil;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.Permission;
import com.dayz.onedayclass.converter.OneDayClassConverter;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.onedayclass.dto.ReadOneDayClassesByCategoryResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("OneDayClassService 단위 테스트")
class OneDayClassServiceTest {

    @InjectMocks
    private OneDayClassService oneDayClassService;

    @Mock
    private OneDayClassRepository oneDayClassRepository;

    @Spy
    private OneDayClassConverter oneDayClassConverter = new OneDayClassConverter(new ImageUrlUtil());

    @Test
    @DisplayName("카테고리 별 원데이 클래스 목록을 조회 할 수 있다.")
    void getOneDayClassesByCategory_success() {

        Permission permission = Permission.of("ROLE_ATELIER");
        Category category = Category.of("도자기");
        Address address = Address.of(1L, 1L, 1L, "서울시", "강남구");
        Member member = Member.of(1L, "김유저", "kakao", "2019948491", null, permission, address);
        Atelier atelier = Atelier.of("개멋진공방", address, "대륭 서초타워 2층", "겁나 멋진 공방입니다.", WorkTime.of(32400000L, 68400000L), "123456789", member);

        List<OneDayClass> oneDayClasses = List.of(
                OneDayClass.of(1L, "도자기 만들기 클래스 1", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>()),
                OneDayClass.of(2L, "도자기 만들기 클래스 2", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>()),
                OneDayClass.of(3L, "도자기 만들기 클래스 3", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>()),
                OneDayClass.of(4L, "도자기 만들기 클래스 4", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>()),
                OneDayClass.of(5L, "도자기 만들기 클래스 5", "도자기 만들기 입니다.", 14000, 50000L, 10, category, atelier, new ArrayList<>())
                );
        Page<OneDayClass> oneDayClassPage = new PageImpl<>(oneDayClasses);

        List<ReadOneDayClassesByCategoryResult> oneDayClassesByCategoryResults = List.of(
                ReadOneDayClassesByCategoryResult.of(1L, "도자기 만들기 클래스 1", "도자기 만들기 입니다.", null),
                ReadOneDayClassesByCategoryResult.of(2L, "도자기 만들기 클래스 2", "도자기 만들기 입니다.", null),
                ReadOneDayClassesByCategoryResult.of(3L, "도자기 만들기 클래스 3", "도자기 만들기 입니다.", null),
                ReadOneDayClassesByCategoryResult.of(4L, "도자기 만들기 클래스 4", "도자기 만들기 입니다.", null),
                ReadOneDayClassesByCategoryResult.of(5L, "도자기 만들기 클래스 5", "도자기 만들기 입니다.", null)
        );

        Page<ReadOneDayClassesByCategoryResult> readOneDayClassesByCategoryResultPage = new PageImpl<ReadOneDayClassesByCategoryResult>(oneDayClassesByCategoryResults);

        PageRequest pageRequest = CustomPageRequest.of(
                0,
                5,
                CustomSort.of("createdAt", "DESC"))
                .convertToPageRequest(OneDayClass.class);
        given(oneDayClassRepository.findOneDayClassByCategoryId(1L, 1L, 1L, pageRequest))
                .willReturn(oneDayClassPage);

        CustomPageResponse<ReadOneDayClassesByCategoryResult> result = this.oneDayClassService
                .getOneDayClassesByCategory(member, 1L, pageRequest);

        assertThat(result, notNullValue());
        assertThat(result.getList(), samePropertyValuesAs(readOneDayClassesByCategoryResultPage.getContent()));

    }

}
