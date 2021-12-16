package com.dayz.post.service;

import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.follow.domain.Follow;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.post.converter.PostConverter;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.ReadPostDetailResponse;
import com.dayz.post.dto.ReadPostDetailsResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    private final PostConverter postConverter;

    @Transactional
    public Long save(PostCreateRequest request) {
        return postRepository.save(postConverter.convertToPost(request)).getId();
    }

    public ReadPostDetailResponse getPostDetail(Long postId) {
        Post foundPost = postRepository.findDetailPostById(postId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.POST_NOT_FOUND));

        return postConverter.convertToReadPostDetailResponse(foundPost);
    }

    public CustomPageResponse<ReadPostDetailsResult> getPostDetails(Long memberId, Pageable pageRequest) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        List<Long> ids = foundMember.getFollows().stream()
                .map(Follow::getId)
                .collect(Collectors.toList());

        Page<ReadPostDetailsResult> readPostDetailsResultPage = postRepository.findPostsByFollows(ids, pageRequest)
                .map(postConverter::convertToReadPostDetailsResult);

        return CustomPageResponse.<ReadPostDetailsResult>of(readPostDetailsResultPage);
    }

}
