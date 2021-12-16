package com.dayz.follow.converter;

import com.dayz.follow.domain.Follow;
import com.dayz.follow.dto.ReadAllFollowingResponse;
import org.springframework.stereotype.Component;

@Component
public class FollowConverter {

    public ReadAllFollowingResponse convertToFollowingResult(Follow follow) {
        return ReadAllFollowingResponse.of(
                follow.getAtelier().getId(),
                follow.getAtelier().getName(),
                follow.getAtelier().getMember().getProfileImageUrl(),
                follow.getAtelier().getIntro()
        );
    }
}
