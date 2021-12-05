package com.dayz.atelier.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@Embeddable
public class WorkTime {

    @Column(name = "work_start_time")
    private Long startTime;

    @Column(name = "work_end_time")
    private Long endTime;

    public static WorkTime of(Long startTime, Long endTime) {
        WorkTime workTime = new WorkTime();
        workTime.setStartTime(startTime);
        workTime.setEndTime(endTime);

        return workTime;
    }

}
