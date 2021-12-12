package com.dayz.member.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dayz.member.converter.AddressConverter;
import com.dayz.member.converter.MemberConverter;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.dto.EditMemberAddressResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("MemberService 단위테스트")
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AddressRepository addressRepository;

    @Spy
    private AddressConverter addressConverter;

    @Spy
    private MemberConverter memberConverter;

    @Test
    @DisplayName("사용자의 관심지역을 변경 할 수 있다.")
    void updateMemberAddress_success() {
        // given
        Long memberId = 1L;
        Long cityIdForEdit = 1L;
        Long regionIdForEdit = 2L;
        Address address = Address.of(1L, cityIdForEdit, regionIdForEdit, "서울시", "강남구");
        Address editedAddress = Address.of(2L, cityIdForEdit, regionIdForEdit, "서울시", "강북구");
        Member member = Member.of(memberId, "김유저2", "kakao", "2019948491", null, Permission.of(1L, "ROLE_USER"), address);

        given(addressRepository.findByCityIdAndRegionId(cityIdForEdit, regionIdForEdit))
                .willReturn(Optional.of(editedAddress));

        // when
        EditMemberAddressResponse editMemberAddressResponse = memberService.editMemberAddress(cityIdForEdit, regionIdForEdit, member);

        // then
        assertThat(editMemberAddressResponse, notNullValue());
        assertThat(editMemberAddressResponse, samePropertyValuesAs(addressConverter.convertToSaveMemberAddressResponse(editedAddress)));
    }

}
