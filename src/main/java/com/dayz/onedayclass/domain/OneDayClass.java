package com.dayz.onedayclass.domain;

import com.dayz.category.domain.Category;
import com.dayz.common.entity.BaseEntity;
import com.dayz.review.domain.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "onedayclass")
public class OneDayClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onedayclass_id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "intro", length = 1000, nullable = false)
    private String intro;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "required_time", nullable = false)
    private Long requiredTime;

    @Column(name = "max_people_number", nullable = false)
    private int maxPeopleNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static OneDayClass of(Long id, String name, String intro, int price, Long requiredTime, int maxPeopleNumber, Category category) {
        OneDayClass oneDayClass = new OneDayClass();
        oneDayClass.setId(id);
        oneDayClass.setName(name);
        oneDayClass.setIntro(intro);
        oneDayClass.setPrice(price);
        oneDayClass.setRequiredTime(requiredTime);
        oneDayClass.setMaxPeopleNumber(maxPeopleNumber);
        oneDayClass.setCategory(category);

        return oneDayClass;
    }

    public static OneDayClass of(String name, String intro, int price, Long requiredTime, int maxPeopleNumber, Category category) {
        OneDayClass oneDayClass = new OneDayClass();
        oneDayClass.setName(name);
        oneDayClass.setIntro(intro);
        oneDayClass.setPrice(price);
        oneDayClass.setRequiredTime(requiredTime);
        oneDayClass.setMaxPeopleNumber(maxPeopleNumber);
        oneDayClass.setCategory(category);

        return oneDayClass;
    }

}
