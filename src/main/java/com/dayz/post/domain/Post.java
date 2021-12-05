package com.dayz.post.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static Post of(Long id, String content, Member member) {
        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.changeMember(member);

        return post;
    }

    public static Post of(String content, Member member) {
        Post post = new Post();
        post.setContent(content);
        post.changeMember(member);

        return post;
    }

    public void changeMember(Member member) {
        this.setMember(member);
    }

}
