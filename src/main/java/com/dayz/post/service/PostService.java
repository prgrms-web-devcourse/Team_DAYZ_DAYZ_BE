package com.dayz.post.service;

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

    @Transactional
    public Long save(PostCreateRequest request) {
        return postRepository.save(postConverter.PostCreateRequestToPostDto(request)).getId();
    }

}
