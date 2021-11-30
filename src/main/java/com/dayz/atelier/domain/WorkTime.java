package com.dayz.atelier.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WorkTime {

    @Column(name = "work_start_at")
    private Long startAt;

    @Column(name = "work_end_at")
    private Long endAt;

}
