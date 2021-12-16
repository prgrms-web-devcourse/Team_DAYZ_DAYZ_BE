package com.dayz.follow.dto;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long atelierId;

    public static FollowRequest of(Long memberId, Long atelierId) {
        FollowRequest followRequest = new FollowRequest();
        followRequest.setMemberId(memberId);
        followRequest.setAtelierId(atelierId);

        return followRequest;
    }
}