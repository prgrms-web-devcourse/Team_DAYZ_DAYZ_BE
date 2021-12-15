package com.dayz.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.category.domain.Category;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImageRepository;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import com.dayz.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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

    @Autowired
    OneDayClassRepository oneDayClassRepository;

    @Autowired
    AtelierRepository atelierRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("게시글을 생성 할 수 있다")
    public void createPost() throws Exception {

        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Category category = Category.of("도자기");
        Member member = Member.of("TestMember", "kakao", "1231424", "url", permission, address);
        WorkTime workTime = WorkTime.of(2020202020L, 202020202020L);
        Atelier atelier = Atelier.of(1L, "공방", address, "100", "intro", workTime, "01010101010", member);
        OneDayClass oneDayClass = OneDayClass.of(1L,"원데이클래스", "클래스설명", 30000, 2310923000L, 5, category, atelier, new ArrayList<>(), new ArrayList<>());

        atelierRepository.save(atelier);

        Atelier atelier1 = atelierRepository.findAll().get(0);

        memberRepository.save(member);

        oneDayClassRepository.save(oneDayClass);

        OneDayClass oneDayClass1 = oneDayClassRepository.findAll().get(0);

        List<PostImagesRequest> postImagesRequests = new ArrayList<>();

        postImagesRequests.add(PostImagesRequest.of("imageUrl1", 1));
        postImagesRequests.add(PostImagesRequest.of("imageUrl2", 2));
        postImagesRequests.add(PostImagesRequest.of("imageUrl3", 3));

        PostCreateRequest pr = PostCreateRequest.of("Content Test", atelier1.getId(), oneDayClass1.getId(), postImagesRequests);

        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pr)))
                        .andDo(print());
    }

}
