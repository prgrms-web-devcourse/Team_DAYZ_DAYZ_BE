package com.dayz.reservation.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.common.enums.ReservationStatus;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClassTime;
import java.time.LocalDate;
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
import org.springframework.util.Assert;

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

    @Column(name = "people_number")
    private int peopleNumber;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onedayclass_time_id")
    private OneDayClassTime oneDayClassTime;

    public static Reservation of(Long id,int peopleNumber, int price, LocalDate date,
            Member member, OneDayClassTime oneDayClassTime) {
        Assert.notNull(id,"Reservation id null 입니다.");
        Assert.isTrue(peopleNumber>=0,"Reservation peopleNumber은 0이상이어야 합니다.");
        Assert.isTrue(price>=0,"Reservation price 0이상이어야 합니다.");
        Assert.notNull(date,"Reservation date null 입니다.");
        Assert.notNull(member,"Reservation member null 입니다.");
        Assert.notNull(oneDayClassTime,"Reservation oneDayClassTime null 입니다.");

        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setPeopleNumber(peopleNumber);
        reservation.setPrice(price);
        reservation.setStatus(ReservationStatus.ACCEPTED);
        reservation.setReservationDate(date);
        reservation.changeMember(member);
        reservation.changeOneDayClassTime(oneDayClassTime);

        return reservation;
    }

    public static Reservation of(int peopleNumber, int price, LocalDate date,
            Member member, OneDayClassTime oneDayClassTime) {

        Assert.isTrue(peopleNumber>=0,"Reservation peopleNumber은 0이상이어야 합니다.");
        Assert.isTrue(price>=0,"Reservation price 0이상이어야 합니다.");
        Assert.notNull(date,"Reservation date null 입니다.");
        Assert.notNull(member,"Reservation member null 입니다.");
        Assert.notNull(oneDayClassTime,"Reservation oneDayClassTime null 입니다.");

        Reservation reservation = new Reservation();
        reservation.setPeopleNumber(peopleNumber);
        reservation.setPrice(price);
        reservation.setStatus(ReservationStatus.ACCEPTED);
        reservation.setReservationDate(date);
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

    @Override
    public void changeUseFlag(boolean useFlag) {
        super.changeUseFlag(useFlag);
    }
}
