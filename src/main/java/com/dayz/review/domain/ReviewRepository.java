package com.dayz.review.domain;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long>,CustomRepository{

    //나의 후기를 조회
    Page<Review> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    //공방별 후기 조회
    Page<Review> findAllByAtelierId(@Param("id") Long id, Pageable pageable);

}
