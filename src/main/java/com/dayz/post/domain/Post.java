package com.dayz.post.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onedayclass_id")
    private OneDayClass oneDayClass;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> postImages = new ArrayList<>();

    public static Post of(Long id, String content, Member member, OneDayClass oneDayClass) {
        Assert.notNull(content, "Content must not be null.");
        Assert.notNull(member, "Member must not be null.");
        Assert.notNull(oneDayClass, "OneDayClass must not be null.");

        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.changeMember(member);
        post.changeOneDayClass(oneDayClass);

        return post;
    }

    public static Post of(String content, Member member, OneDayClass oneDayClass) {
        Assert.notNull(content, "Content must not be null.");
        Assert.notNull(member, "Member must not be null.");
        Assert.notNull(oneDayClass, "OneDayClass must not be null.");

        Post post = new Post();
        post.setContent(content);
        post.changeMember(member);
        post.changeOneDayClass(oneDayClass);

        return post;
    }

    public static Post of(Long id, String content, Member member, OneDayClass oneDayClass, List<PostImage> postImages) {
        Assert.notNull(content, "Content must not be null.");
        Assert.notNull(member, "Member must not be null.");
        Assert.notNull(oneDayClass, "OneDayClass must not be null.");

        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.changeMember(member);
        post.changeOneDayClass(oneDayClass);
        postImages.forEach(post::addPostImage);

        return post;
    }

    public static Post of(String content, Member member, OneDayClass oneDayClass, List<PostImage> postImages) {
        Assert.notNull(content, "Content must not be null.");
        Assert.notNull(member, "Member must not be null.");
        Assert.notNull(oneDayClass, "OneDayClass must not be null.");

        Post post = new Post();
        post.setContent(content);
        post.changeMember(member);
        post.changeOneDayClass(oneDayClass);
        postImages.forEach(post::addPostImage);

        return post;
    }

    public void changeMember(Member member) {
        this.setMember(member);
    }

    public void addPostImages(List<PostImage> postImageList) {
        this.postImages = postImageList;
    }

    public void changeOneDayClass(OneDayClass oneDayClass) {
        this.setOneDayClass(oneDayClass);
    }

    public void addPostImage(PostImage postImage) {
        postImage.changePost(this);
    }

}
