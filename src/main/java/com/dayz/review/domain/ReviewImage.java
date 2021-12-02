package com.dayz.review.domain;


import com.dayz.common.entity.BaseEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "post_image")
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_uuid", nullable = false)
    private UUID imageUuid;

    @Column(name = "sequence")
    private int sequence;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

}
