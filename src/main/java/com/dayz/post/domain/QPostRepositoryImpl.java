package com.dayz.post.domain;

import static com.dayz.atelier.domain.QAtelier.atelier;
import static com.dayz.member.domain.QMember.member;
import static com.dayz.post.domain.QPost.post;

import com.dayz.atelier.domain.QAtelier;
import com.dayz.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QPostRepositoryImpl implements QPostRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Post> findDetailPostById(Long postId) {

        Post foundPost = jpaQueryFactory.selectFrom(QPost.post)
                .innerJoin(QPost.post.member, member).fetchJoin()
                .innerJoin(member.atelier, atelier).fetchJoin()
                .where(QPost.post.id.eq(postId)
                        .and(QPost.post.useFlag.eq(true)))
                .fetchOne();

        return Optional.of(foundPost);
    }

}
