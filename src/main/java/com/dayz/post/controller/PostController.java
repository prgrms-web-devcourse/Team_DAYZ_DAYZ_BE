package com.dayz.post.controller;

import com.dayz.common.dto.ApiResponse;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse createPost(@RequestBody @Valid PostCreateRequest request) {
        return ApiResponse.ok(postService.save(request));
    }

}
