package com.dayz.follow.service;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.follow.converter.FollowConverter;
import com.dayz.follow.domain.Follow;
import com.dayz.follow.domain.FollowRepository;
import com.dayz.follow.dto.ReadAllFollowingResponse;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final MemberRepository memberRepository;

    private final AtelierRepository atelierRepository;

    private final FollowConverter followConverter;

    public boolean followingUnfollowing(Long memberId, Long atelierId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
        Atelier atelier = atelierRepository.findById(atelierId).orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));

        if (followRepository.existsByMemberIdAndAtelierId(memberId, atelierId)) {
            Follow follow = followRepository.findByMemberIdAndAtelierId(memberId, atelierId);
            follow.changeUseFlag(false);
            followRepository.save(follow);
            return false;
        } else {
            followRepository.save(Follow.of(member, atelier));
            return true;
        }
    }

    public CustomPageResponse getAllFollowings(CustomPageRequest pageRequest, Long memberId) {
        PageRequest pageable = pageRequest.convertToPageRequest(Follow.class);
        Page<ReadAllFollowingResponse> readAllFollowingResponses =
                followRepository.findAllByMemberIdAndUseFlagIsTrue(memberId, pageable)
                        .map(followConverter::convertToFollowingResult);

        return CustomPageResponse.of(readAllFollowingResponses);
    }

}