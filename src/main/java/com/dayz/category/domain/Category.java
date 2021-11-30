package com.dayz.category.domain;

import com.dayz.common.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
@Table(
        name="category",
        uniqueConstraints = {
        @UniqueConstraint(name = "UK_category_name", columnNames = {"name"})}
)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false,length = 50)
    private String name;

}
