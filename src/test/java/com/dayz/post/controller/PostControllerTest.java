package com.dayz.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.category.domain.Category;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomSort;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.converter.PostConverter;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.domain.PostImageRepository;
import com.dayz.post.domain.PostRepository;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import com.dayz.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
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

    @Autowired
    PostConverter postConverter;

    @BeforeEach
    void setUp() {
        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Category category = Category.of("도자기");
        OneDayClass oneDayClass = OneDayClass.of(1L,"원데이클래스", "클래스설명", 30000, 2310923000L, 5, category);
        Member member = Member.of(1L,"TestMember", "kakao", "1231424", "url", permission, address);
        WorkTime workTime = WorkTime.of(2020202020L, 202020202020L);
        Atelier atelier = Atelier.of(3L,"공방",address,"100번지","intro",workTime,"0101",member);

        atelierRepository.save(atelier);
        memberRepository.save(member);
        oneDayClassRepository.save(oneDayClass);

        Atelier atelier1 = atelierRepository.findAll().get(0);
        Member member1 = memberRepository.findAll().get(0);
        member1.changeAtelier(atelier1);

        Post post1 = Post.of(1L, "content1", member1, oneDayClass);
        Post post2 = Post.of(2L, "content2", member1, oneDayClass);
        Post post3 = Post.of(3L, "content3", member1, oneDayClass);
        Post post4 = Post.of(4L, "content4", member1, oneDayClass);
        Post post5 = Post.of(5L, "content5", member1, oneDayClass);

        List<PostImage> postImagesRequests = new ArrayList<>();

        postImagesRequests.add(PostImage.of("image1", 1));
        postImagesRequests.add(PostImage.of("image2", 2));
        postImagesRequests.add(PostImage.of("image3", 3));
        post1.addPostImages(postImagesRequests);
        postRepository.save(post1);

        postImagesRequests.clear();
        postImagesRequests.add(PostImage.of("image1", 1));
        postImagesRequests.add(PostImage.of("image2", 2));
        postImagesRequests.add(PostImage.of("image3", 3));
        post2.addPostImages(postImagesRequests);
        postRepository.save(post2);

        postImagesRequests.clear();
        postImagesRequests.add(PostImage.of("image1", 1));
        postImagesRequests.add(PostImage.of("image2", 2));
        postImagesRequests.add(PostImage.of("image3", 3));
        post3.addPostImages(postImagesRequests);
        postRepository.save(post3);

        postImagesRequests.clear();
        postImagesRequests.add(PostImage.of("image1", 1));
        postImagesRequests.add(PostImage.of("image2", 2));
        postImagesRequests.add(PostImage.of("image3", 3));
        post4.addPostImages(postImagesRequests);
        postRepository.save(post4);

        postImagesRequests.clear();
        postImagesRequests.add(PostImage.of("image1", 1));
        postImagesRequests.add(PostImage.of("image2", 2));
        postImagesRequests.add(PostImage.of("image3", 3));
        post5.addPostImages(postImagesRequests);
        postRepository.save(post5);
    }

    @Test
    @DisplayName("게시글을 생성 할 수 있다")
    public void createPost() throws Exception {

        Atelier atelier1 = atelierRepository.findAll().get(0);

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

    @Test
    @DisplayName("공방의 게시글 목록을 불러온다")
    public void readAllPosts() throws Exception{

        Atelier atelier = atelierRepository.findAll().get(0);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/posts/ateliers/{atelierId}", atelier.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomPageRequest.of(0, 10, CustomSort.of("createdAt", "ASC")))))
                .andDo(print());

    }
}