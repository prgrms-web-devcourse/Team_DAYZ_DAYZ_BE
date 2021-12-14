package com.dayz.follow.service;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.follow.domain.Follow;
import com.dayz.follow.domain.FollowRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final MemberRepository memberRepository;

    private final AtelierRepository atelierRepository;

    public Long following(Long memberId, Long atelierId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
        Atelier atelier = atelierRepository.findById(atelierId).orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));

        return followRepository.save(Follow.of(member, atelier)).getId();
    }
    public void unFollowing(Long memberId, Long atelierId) {
        Follow followFindByIds = followRepository.findByMemberIdAndAtelierId(memberId, atelierId);
        followRepository.deleteById(followFindByIds.getId());
    }
}
