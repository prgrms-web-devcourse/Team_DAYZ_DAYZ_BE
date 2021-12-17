package com.dayz.member.converter;

import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.dto.EditMemberProfileResponse;
import com.dayz.member.dto.ReadMemberInfoResponse;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MemberConverter {

    public ReadMemberInfoResponse convertToReadMemberInfoResponse(Member member, String token) {
        Assert.notNull(member, "member must not be null!");
        Assert.notNull(token, "token must not be null!");

        Address address = member.getAddress();
        Long atelierId = Objects.isNull(member.getAtelier()) ? null : member.getAtelier().getId();

        return ReadMemberInfoResponse.of(
                member.getId(),
                token,
                member.getUsername(),
                member.getProfileImageUrl(),
                address.getCityId(),
                address.getCityName(),
                address.getRegionId(),
                address.getRegionName(),
                member.getPermission().getName(),
                atelierId
        );

    }

    public EditMemberProfileResponse convertToEditMemberProfileResponse(Member member) {
        return EditMemberProfileResponse.of(member.getUsername(), member.getProfileImageUrl());
    }

}
