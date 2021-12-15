package com.dayz.post.service;

import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.post.converter.PostConverter;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.ReadPostDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

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

}
