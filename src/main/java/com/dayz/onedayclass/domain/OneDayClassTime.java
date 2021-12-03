package com.dayz.onedayclass.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.common.enums.TimeStatus;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "onedayclass_time")
public class OneDayClassTime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_date", nullable = false)
    private LocalDateTime class_date;

    @Column(name = "start_at", nullable = false)
    private Long start_at;

    @Column(name = "end_at", nullable = false)
    private Long end_at;

    @Enumerated(EnumType.STRING)
    private TimeStatus status;

    @ManyToOne
    @JoinColumn(name = "onedayclass_id")
    private OneDayClass oneDayClass;

}
