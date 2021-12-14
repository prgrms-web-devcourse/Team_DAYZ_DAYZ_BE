package com.dayz.review.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
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
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "score")
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onedayclass_id")
    private OneDayClass oneDayClass;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> reviewImages = new ArrayList<>();


    public static Review of(Long id,
        String title,
        String content,
        int score,
        Member member,
        OneDayClass oneDayClass,List<ReviewImage> reviewImages
    ) {
        Assert.notNull(id,"Review id 값이 null입니다");
        Assert.notNull(title,"Review title이 null 입니다.");
        Assert.notNull(content,"Review content이 null 입니다.");
        Assert.notNull(score,"Review score이 null 입니다.");
        Assert.notNull(member,"Review score이 null 입니다.");
        Assert.notNull(oneDayClass,"Review score이 null 입니다.");

        Review review = new Review();
        review.setId(id);
        review.setTitle(title);
        review.setContent(content);
        review.setScore(score);
        review.changeMember(member);
        review.changeOneDayClass(oneDayClass);
        if(Objects.nonNull(reviewImages) &&reviewImages.size()>0){
            review.addReviewImage(reviewImages);
        }

        return review;
    }

    public static Review of(String title,
        String content,
        int score,
        Member member,
        OneDayClass oneDayClass,
        List<ReviewImage> reviewImages
    ) {
        Assert.notNull(title,"Review title이 null 입니다.");
        Assert.notNull(content,"Review content이 null 입니다.");
        Assert.notNull(score,"Review score이 null 입니다.");
        Assert.notNull(member,"Review score이 null 입니다.");
        Assert.notNull(oneDayClass,"Review score이 null 입니다.");

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setScore(score);
        review.changeMember(member);
        review.changeOneDayClass(oneDayClass);
        if(Objects.nonNull(reviewImages) &&reviewImages.size()>0){
            review.addReviewImage(reviewImages);
        }

        return review;
    }

    public static Review of(String title,
        String content,
        int score,
        Member member,
        OneDayClass oneDayClass
    ) {
        Assert.notNull(title,"Review title이 null 입니다.");
        Assert.notNull(content,"Review content이 null 입니다.");
        Assert.notNull(score,"Review score이 null 입니다.");
        Assert.notNull(member,"Review score이 null 입니다.");
        Assert.notNull(oneDayClass,"Review score이 null 입니다.");

        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setScore(score);
        review.changeMember(member);
        review.changeOneDayClass(oneDayClass);

        return review;
    }


    public void changeMember(Member member) {
        this.setMember(member);
    }

    public void changeOneDayClass(OneDayClass oneDayClass) {
        this.setOneDayClass(oneDayClass);
    }

    public void addReviewImage(List<ReviewImage> reviewImages){
        reviewImages.forEach(reviewImage1->reviewImage1.changeReview(this));
    }

}
