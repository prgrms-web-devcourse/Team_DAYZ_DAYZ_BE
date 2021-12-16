package com.dayz.onedayclass.domain;

import static com.dayz.atelier.domain.QAtelier.atelier;
import static com.dayz.category.domain.QCategory.category;
import static com.dayz.member.domain.QAddress.address;
import static com.dayz.onedayclass.domain.QOneDayClass.oneDayClass;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class QOneDayClassRepositoryImpl implements QOneDayClassRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<OneDayClass> findOneDayClassByCategoryId(
            Long categoryId,
            Long cityId,
            Long regionId,
            Pageable pageRequest) {

        JPAQuery<OneDayClass> contentQuery = jpaQueryFactory.selectFrom(oneDayClass)
                .innerJoin(oneDayClass.atelier, atelier).fetchJoin()
                .innerJoin(atelier.address, address).fetchJoin()
                .innerJoin(oneDayClass.category, category).fetchJoin()
                .where(oneDayClass.category.id.eq(categoryId)
                        .and(atelier.address.cityId.eq(cityId))
                        .and(atelier.address.regionId.eq(regionId)))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize());

        for (Sort.Order o : pageRequest.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(oneDayClass.getType(), oneDayClass.getMetadata());
            contentQuery.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<OneDayClass> content = contentQuery.fetch();

        JPAQuery<OneDayClass> countQuery = jpaQueryFactory.selectFrom(oneDayClass)
                .innerJoin(oneDayClass.atelier, atelier).fetchJoin()
                .innerJoin(oneDayClass.category, category).fetchJoin()
                .innerJoin(oneDayClass.atelier.address, address).fetchJoin()
                .where(oneDayClass.category.id.eq(categoryId)
                        .and(oneDayClass.atelier.address.cityId.eq(cityId))
                        .and(oneDayClass.atelier.address.regionId.eq(regionId)));

        return PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);
    }

}
