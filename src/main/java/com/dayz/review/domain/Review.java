package com.dayz.review.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
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
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static Review of(Long id,
            String title,
            String content,
            int score,
            Member member,
            OneDayClass oneDayClass
    ) {
        Review review = new Review();
        review.setId(id);
        review.setTitle(title);
        review.setContent(content);
        review.setScore(score);
        review.changeMember(member);
        review.changeOneDayClass(oneDayClass);

        return review;
    }

    public static Review of(String title,
            String content,
            int score,
            Member member,
            OneDayClass oneDayClass
    ) {
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

}
