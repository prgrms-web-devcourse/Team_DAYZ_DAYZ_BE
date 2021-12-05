package com.dayz.onedayclass.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.common.enums.TimeStatus;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "onedayclass_time")
public class OneDayClassTime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_date", nullable = false)
    private LocalDateTime classDate;

    @Column(name = "start_time", nullable = false)
    private Long startTime;

    @Column(name = "end_time", nullable = false)
    private Long endTime;

    @Enumerated(EnumType.STRING)
    private TimeStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onedayclass_id")
    private OneDayClass oneDayClass;

    public static OneDayClassTime of(Long id,
            LocalDateTime classDate,
            Long startTime,
            Long endTime,
            TimeStatus status,
            OneDayClass oneDayClass
    ) {
        OneDayClassTime oneDayClassTime = new OneDayClassTime();
        oneDayClassTime.setId(id);
        oneDayClassTime.setClassDate(classDate);
        oneDayClassTime.setStartTime(startTime);
        oneDayClassTime.setEndTime(endTime);
        oneDayClassTime.setStatus(status);
        oneDayClassTime.changeOneDayClass(oneDayClass);

        return oneDayClassTime;
    }

    public static OneDayClassTime of(LocalDateTime classDate,
            Long startTime,
            Long endTime,
            TimeStatus status,
            OneDayClass oneDayClass
    ) {
        OneDayClassTime oneDayClassTime = new OneDayClassTime();
        oneDayClassTime.setClassDate(classDate);
        oneDayClassTime.setStartTime(startTime);
        oneDayClassTime.setEndTime(endTime);
        oneDayClassTime.setStatus(status);
        oneDayClassTime.changeOneDayClass(oneDayClass);

        return oneDayClassTime;
    }

    public void changeOneDayClass(OneDayClass oneDayClass) {
        this.setOneDayClass(oneDayClass);
    }

}
