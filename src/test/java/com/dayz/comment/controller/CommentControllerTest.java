package com.dayz.comment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.dayz.category.domain.Category;
import com.dayz.comment.domain.Comment;
import com.dayz.comment.domain.CommentRepository;
import com.dayz.comment.dto.CommentCreateRequest;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.Address;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.post.domain.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("댓글을 등록할 수 있다.")
    public void createComment() throws Exception{
        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Category category = Category.of("도자기");
        OneDayClass oneDayClass = OneDayClass.of("원데이클래스", "클래스설명", 30000, 2310923000L, 5, category);
        Member member = Member.of("TestMember", "kakao", "1231424", "url", permission, address);

        Post post = Post.of("Post Content", member, oneDayClass);

        CommentCreateRequest ccr = new CommentCreateRequest();
        ccr.setContent("댓글테스트");
        ccr.setPost(post);
        ccr.setMember(member);

        mockMvc.perform(post("/api/v1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ccr)))
                .andDo(print());
    }

}