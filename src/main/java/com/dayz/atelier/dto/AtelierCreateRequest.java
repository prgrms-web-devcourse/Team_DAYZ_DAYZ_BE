package com.dayz.atelier.dto;

import com.dayz.atelier.domain.WorkTime;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtelierCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Address address;

    @NotBlank
    private String detail;

    @NotBlank
    private String intro;

    @NotNull
    private WorkTime workTime;

    @NotBlank
    private String businessNumber;

    @NotBlank
    private String profileImageUrl;

    @NotNull
    private Member member;

}
