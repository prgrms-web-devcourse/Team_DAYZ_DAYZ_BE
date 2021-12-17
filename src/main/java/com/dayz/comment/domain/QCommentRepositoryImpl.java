package com.dayz.comment.domain;

import static com.dayz.comment.domain.QComment.comment;
import static com.dayz.member.domain.QMember.member;
import static com.dayz.post.domain.QPost.post;

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
public class QCommentRepositoryImpl implements QCommentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Comment> findAllByPostId(Long postId, Pageable pageRequest) {

        JPAQuery<Comment> contentQuery = jpaQueryFactory.selectFrom(comment)
                .innerJoin(comment.post, post).fetchJoin()
                .innerJoin(comment.member, member).fetchJoin()
                .where(post.id.eq(postId)
                        .and(comment.useFlag.eq(true)))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize());

        for (Sort.Order o : pageRequest.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(comment.getType(), comment.getMetadata());
            contentQuery.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<Comment> content = contentQuery.fetch();

        JPAQuery<Comment> countQuery = jpaQueryFactory.selectFrom(comment)
                .innerJoin(comment.post, post).fetchJoin()
                .innerJoin(comment.member, member).fetchJoin()
                .where(post.id.eq(postId)
                        .and(comment.useFlag.eq(true)));

        return PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);
    }

}
