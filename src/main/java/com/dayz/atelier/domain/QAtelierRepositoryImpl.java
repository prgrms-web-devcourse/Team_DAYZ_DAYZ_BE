package com.dayz.atelier.domain;

import static com.dayz.atelier.domain.QAtelier.atelier;
import static com.dayz.member.domain.QAddress.address;
import static com.dayz.member.domain.QMember.member;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class QAtelierRepositoryImpl implements QAtelierRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Atelier> findAteliersByAddress(
            @Param("cityId") Long cityId,
            @Param("regionId") Long regionId,
            Pageable pageRequest
    ) {
        List<Atelier> content = jpaQueryFactory.selectFrom(atelier)
                .innerJoin(atelier.member, member).fetchJoin()
                .innerJoin(atelier.address, address).fetchJoin()
                .where(address.cityId.eq(cityId)
                        .and(atelier.address.regionId.eq(regionId))
                        .and(atelier.address.useFlag.eq(true))
                ).offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<Atelier> countQuery = jpaQueryFactory.selectFrom(atelier)
                .innerJoin(atelier.address, address)
                .where(atelier.address.cityId.eq(cityId)
                        .and(atelier.address.regionId.eq(regionId))
                        .and(atelier.useFlag.eq(true))
                );

        return PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);
    }

}
