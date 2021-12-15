package com.dayz.post.service;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.converter.PostConverter;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostConverter postConverter;

    private final AtelierRepository atelierRepository;

    private final OneDayClassRepository oneDayClassRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(PostCreateRequest request) {
        Atelier atelier = atelierRepository.findAtelierByIdAAndUseFlagIsTrue(request.getAtelierId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));
        Member member = memberRepository.findMemberByIdAndUseFlagIsTrue(atelier.getMember().getId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
        OneDayClass oneDayClass = oneDayClassRepository.findOneDayClassByIdAAndUseFlagIsTrue(request.getOneDayClassId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.ONEDAYCLASS_NOT_FOUND));

        return postRepository.save(
                postConverter.convertToPost(
                        request.getContent(),
                        member,
                        oneDayClass,
                        request.getPostImages())).getId();
    }

}
