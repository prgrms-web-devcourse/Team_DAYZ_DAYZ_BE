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

    //원데이 클래스별 후기 조회
    Page<Review> findAllByOneDayClassId(@Param("id") Long id, Pageable pageable);

    @Query("select coalesce(avg(r.score),0) from Review r where r.oneDayClass.id= :classId and r.useFlag = true")
    double getReviewAverageByOneDayClass(@Param("classId") Long classId);

    @Query("select coalesce(avg(r.score),0) from Review r where r.oneDayClass.atelier.id= :atelierId and r.useFlag = true")
    double getReviewAverageByAtelier(@Param("atelierId") Long atelierId);
}
