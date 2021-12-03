package com.dayz.reservation.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.common.enums.ReservationStatus;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClassTime;
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
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
@Table(
        name = "reservation",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_reservation_reservation_code", columnNames = {"reservation_code"})
        }
)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "people_number")
    private int peopleNumber;

    @Column(name = "price")
    private int price;

    @Column(name = "reservation_code", nullable = false, length = 100)
    private String code;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "onedayclass_time_id")
    private OneDayClassTime oneDayClassTime;

}
