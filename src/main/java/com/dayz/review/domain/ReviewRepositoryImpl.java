package com.dayz.review.domain;

import static org.springframework.util.StringUtils.isEmpty;

import com.dayz.member.domain.QMember;
import com.dayz.onedayclass.domain.QOneDayClass;
import com.querydsl.core.QueryResults;

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
public class ReviewRepositoryImpl implements CustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findAllByMemberId(Long memberId, Pageable pageable) {

        List<OrderSpecifier> orderlist = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(QReview.review.getType(),
                QReview.review.getMetadata());
            orderlist.add(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                pathBuilder.get(o.getProperty())));
        }
        QueryResults<Review> results = jpaQueryFactory
            .selectFrom(QReview.review)
            .innerJoin(QReview.review.member, QMember.member).fetchJoin()
            .innerJoin(QReview.review.oneDayClass, QOneDayClass.oneDayClass).fetchJoin()
            .where(QReview.review.useFlag.eq(true),
                QMember.member.id.eq(memberId))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderlist.stream().toArray(OrderSpecifier[]::new))
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Review> findAllByAtelierId(Long id, Pageable pageable) {
        List<OrderSpecifier> orderlist = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(QReview.review.getType(),
                QReview.review.getMetadata());
            orderlist.add(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                pathBuilder.get(o.getProperty())));
        }

        QueryResults<Review> results = jpaQueryFactory
            .selectFrom(QReview.review)
            .innerJoin(QReview.review.member, QMember.member).fetchJoin()
            .innerJoin(QReview.review.oneDayClass, QOneDayClass.oneDayClass).fetchJoin()
            .where(QReview.review.useFlag.eq(true),
                QOneDayClass.oneDayClass.atelier.id.eq(id))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderlist.stream().toArray(OrderSpecifier[]::new))
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());

    }

    @Override
    public Page<Review> findAllByOneDayClassId(Long id, Pageable pageable) {
        List<OrderSpecifier> orderlist = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(QReview.review.getType(),
                QReview.review.getMetadata());
            orderlist.add(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                pathBuilder.get(o.getProperty())));
        }

        QueryResults<Review> results = jpaQueryFactory
            .selectFrom(QReview.review)
            .innerJoin(QReview.review.member, QMember.member).fetchJoin()
            .innerJoin(QReview.review.oneDayClass, QOneDayClass.oneDayClass).fetchJoin()
            .where(QReview.review.useFlag.eq(true),
                QOneDayClass.oneDayClass.id.eq(id))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderlist.stream().toArray(OrderSpecifier[]::new))
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());

    }

}
