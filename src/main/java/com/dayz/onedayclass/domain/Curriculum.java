package com.dayz.onedayclass.domain;

import com.dayz.common.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "curriculum")
public class Curriculum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step", nullable = false)
    private int step;

    @Column(name = "content", length = 1000, nullable = false)
    private String content;

    @Column(name = "use_flag")
    private boolean use_flag;

}
