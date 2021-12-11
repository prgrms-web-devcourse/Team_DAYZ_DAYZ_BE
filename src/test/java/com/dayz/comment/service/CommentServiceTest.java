package com.dayz.comment.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import com.dayz.category.domain.Category;
import com.dayz.comment.domain.Comment;
import com.dayz.comment.domain.CommentRepository;
import com.dayz.comment.dto.CommentCreateRequest;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.Permission;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.post.domain.Post;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("CommentRepository에서 PostId를 통해 데이터를 조회한다")
    public void findCommentsByPostId() {
        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Category category = Category.of("도자기");
        OneDayClass oneDayClass = OneDayClass.of("원데이클래스", "클래스설명", 30000, 2310923000L, 5, category);
        Member member = Member.of("TestMember", "kakao", "1231424", "url", permission, address);

        Post post = Post.of("Post Content", member, oneDayClass);

        Comment comment = Comment.of("Comment Test", post, member);

        commentRepository.save(comment);

        List<Comment> foundedComments = commentRepository.findAllByPost(post.getId());

        assertThat(foundedComments.get(0).getContent(), is("Comment Test"));
    }

    @Test
    @DisplayName("CommentService를 통해 댓글을 등록한다")
    public void createCommentviaService() {
        Permission permission = Permission.of("USER");
        Address address = Address.of(1L, 1L, "서울시", "송파구");
        Category category = Category.of("도자기");
        OneDayClass oneDayClass = OneDayClass.of("원데이클래스", "클래스설명", 30000, 2310923000L, 5, category);
        Member member = Member.of("TestMember", "kakao", "1231424", "url", permission, address);

        Post post = Post.of("Post Content", member, oneDayClass);

        Comment comment = Comment.of("Comment Test", post, member);

        CommentCreateRequest ccr = new CommentCreateRequest();
        ccr.setContent("댓글테스트");
        ccr.setPost(post);
        ccr.setMember(member);

        commentService.save(ccr);
    }
}