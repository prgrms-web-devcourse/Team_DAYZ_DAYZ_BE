package com.dayz.onedayclass.domain;

import com.dayz.category.domain.Category;
import com.dayz.review.domain.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
@Table(name = "onedayclass")
public class OneDayClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "intro", length = 1000, nullable = false)
    private String intro;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "required_time", nullable = false)
    private Long required_time;

    @Column(name = "max_people_number", nullable = false)
    private int max_people_number;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
