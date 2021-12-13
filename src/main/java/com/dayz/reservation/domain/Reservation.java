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
@Table(name = "reservation")

public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "reservation_code", length = 100)
    private String code;

    @Column(name = "people_number")
    private int peopleNumber;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onedayclass_time_id")
    private OneDayClassTime oneDayClassTime;

    public static Reservation of(Long id, String code, int peopleNumber, int price, ReservationStatus status, LocalDateTime date,
            Member member, OneDayClassTime oneDayClassTime) {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setCode(code);
        reservation.setPeopleNumber(peopleNumber);
        reservation.setPrice(price);
        reservation.setStatus(status);
        reservation.setDate(date);
        reservation.changeMember(member);
        reservation.changeOneDayClassTime(oneDayClassTime);

        return reservation;
    }

    public static Reservation of(String code, int peopleNumber, int price, ReservationStatus status, LocalDateTime date,
            Member member, OneDayClassTime oneDayClassTime) {
        Reservation reservation = new Reservation();
        reservation.setCode(code);
        reservation.setPeopleNumber(peopleNumber);
        reservation.setPrice(price);
        reservation.setStatus(status);
        reservation.setDate(date);
        reservation.changeMember(member);
        reservation.changeOneDayClassTime(oneDayClassTime);

        return reservation;
    }

    public void changeMember(Member member) {
        this.setMember(member);
    }

    public void changeOneDayClassTime(OneDayClassTime oneDayClassTime) {
        this.setOneDayClassTime(oneDayClassTime);
    }

}
