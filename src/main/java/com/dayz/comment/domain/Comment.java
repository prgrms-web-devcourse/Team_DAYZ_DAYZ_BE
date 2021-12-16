package com.dayz.comment.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Member;
import com.dayz.post.domain.Post;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "content", length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Comment of(Long id, String content, Post post, Member member) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent(content);
        comment.changePost(post);
        comment.changeMember(member);

        return comment;
    }

    public static Comment of(String content, Post post, Member member) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.changePost(post);
        comment.changeMember(member);

        return comment;
    }

    public void changePost(Post post) {
        this.setPost(post);
    }

    public void changeMember(Member member) {
        this.setMember(member);
    }

}