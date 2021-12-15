package com.dayz.post.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.member.atelier.id = :atelierId and p.useFlag= true")
    Page<Post> findPostByAtelierIdAndUseFlagTrue(@Param("atelierId") Long atelierId, Pageable pageRequest);
}
