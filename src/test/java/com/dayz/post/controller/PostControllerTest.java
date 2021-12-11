package com.dayz.post.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.dayz.category.domain.Category;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.Permission;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.domain.PostImageRepository;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostImageRepository postImageRepository;

    @Test
    @DisplayName("게시글을 생성 할 수 있다")
    public void createPost() throws Exception {

        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Category category = Category.of("도자기");
        OneDayClass oneDayClass = OneDayClass.of("원데이클래스", "클래스설명", 30000, 2310923000L, 5, category);
        Member member = Member.of("TestMember", "kakao", "1231424", "url", permission, address);

        Post post = Post.of("content Test", member, oneDayClass);
        List<PostImage> postImages = new ArrayList<>();
        postImages.add(PostImage.of("imageUrl1", 1));
        postImages.add(PostImage.of("imageUrl2", 2));
        postImages.add(PostImage.of("imageUrl3", 3));

        PostCreateRequest pr = new PostCreateRequest();
        pr.setContent("Post Test");
        pr.setMember(member);
        pr.setOneDayClass(oneDayClass);
        pr.setPostImages(postImages);

        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pr)))
                        .andDo(print());
    }

}