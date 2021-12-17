package com.dayz.reservation.domain;

import static com.dayz.reservation.domain.QReservation.reservation;
import static com.dayz.reservation.domain.QReservation.reservation;
import com.dayz.common.enums.ReservationStatus;
import com.dayz.member.domain.QMember;
import com.dayz.onedayclass.domain.QOneDayClass;
import com.dayz.onedayclass.domain.QOneDayClassTime;
import com.dayz.reservation.dto.QReadAllAtelierReservationRequest;
import com.dayz.reservation.dto.QReadAllMyReservationRequest;
import com.dayz.reservation.dto.ReadAllAtelierReservationRequest;
import com.dayz.reservation.dto.ReadAllAtelierReservationResponse;
import com.dayz.reservation.dto.ReadAllMyReservationRequest;
import com.dayz.review.domain.QReview;
import com.dayz.review.domain.Review;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class CustomReservationRepositoryImpl implements CustomReservationRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ReadAllMyReservationRequest> findMyReservation(Long memberId, Pageable pageable) {
        List<OrderSpecifier> orderlist = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(reservation.getType(),
                reservation.getMetadata());
            orderlist.add(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                pathBuilder.get(o.getProperty())));
        }

        QueryResults<ReadAllMyReservationRequest> results = jpaQueryFactory
            .select(new QReadAllMyReservationRequest(
                reservation.id,
                reservation.oneDayClassTime.oneDayClass.name,
                reservation.reservationDate,
                reservation.oneDayClassTime.classDate,
                reservation.oneDayClassTime.startTime,
                reservation.oneDayClassTime.endTime,
                reservation.status.stringValue()))
            .from(reservation)
            .leftJoin(reservation.member).on(reservation.member.id.eq(memberId))
            .where(reservation.useFlag.eq(true))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderlist.stream().toArray(OrderSpecifier[]::new))
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<ReadAllAtelierReservationRequest> findAtelierReservation(Long atelierId,
        Pageable pageable) {
        List<OrderSpecifier> orderlist = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(reservation.getType(),
                reservation.getMetadata());
            orderlist.add(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                pathBuilder.get(o.getProperty())));
        }

        QueryResults<ReadAllAtelierReservationRequest> results = jpaQueryFactory
            .select(new QReadAllAtelierReservationRequest(
                reservation.id,
                reservation.oneDayClassTime.oneDayClass.name,
                reservation.reservationDate,
                reservation.oneDayClassTime.classDate,
                reservation.oneDayClassTime.startTime
                ,reservation.oneDayClassTime.endTime
                , reservation.status.stringValue()))
            .from(reservation)
            .leftJoin(reservation.oneDayClassTime,QOneDayClassTime.oneDayClassTime)
            .where(reservation.useFlag.eq(true),
                QOneDayClassTime.oneDayClassTime.oneDayClass.atelier.id.eq(atelierId))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderlist.stream().toArray(OrderSpecifier[]::new))
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());

    }
}
