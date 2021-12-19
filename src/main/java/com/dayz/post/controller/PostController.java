package com.dayz.post.controller;

import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.jwt.JwtAuthentication;
import com.dayz.post.domain.Post;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.ReadPostDetailResponse;
import com.dayz.post.dto.ReadPostDetailsResult;
import com.dayz.post.dto.ReadPostsByAtelierResult;
import com.dayz.post.service.PostService;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return ApiResponse.ok(Map.of("postId", postService.save(request)));
    }

    @GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ReadPostDetailResponse> readPostDetail(@PathVariable("postId") Long postId) {
        ReadPostDetailResponse response = postService.getPostDetail(postId);

        return ApiResponse.<ReadPostDetailResponse>ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse<ReadPostDetailsResult>> readPostDetails(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @Valid CustomPageRequest request
    ) {
        CustomPageResponse<ReadPostDetailsResult> response = postService.getPostDetails(
                authentication.getId(),
                request.convertToPageRequest(Post.class)
        );

        return ApiResponse.<CustomPageResponse<ReadPostDetailsResult>>ok(response);
    }

    @GetMapping(value = "/ateliers/{atelierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse<ReadPostsByAtelierResult> > readPostsByAtelier(
            @PathVariable("atelierId") Long atelierId,
            @Valid CustomPageRequest request) {
        CustomPageResponse<ReadPostsByAtelierResult> response = postService.getPostsByAtelier(atelierId, request.convertToPageRequest(Post.class));

        return ApiResponse.<CustomPageResponse<ReadPostsByAtelierResult>>ok(response);
    }

}
