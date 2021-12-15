package com.dayz.comment.controller;

import com.dayz.comment.dto.CommentCreateRequest;
import com.dayz.comment.service.CommentService;
import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse createComment(CommentCreateRequest request) {
        return ApiResponse.ok(Map.of("commentId",commentService.save(request)));
    }

    @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getAllComments(@PathVariable("postId") Long postId, @RequestBody CustomPageRequest pageRequest) {
        return ApiResponse.ok(commentService.getAllComments(pageRequest, postId));
    }
}
