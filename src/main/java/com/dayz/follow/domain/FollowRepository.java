package com.dayz.follow.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f join fetch f.member m join fetch f.atelier a where m.id = :memberId and a.id = :atelierId and f.useFlag = true")
    Follow findByMemberIdAndAtelierId(@Param("memberId") Long memberId, @Param("atelierId") Long atelierId);

    boolean existsByMemberIdAndAtelierIdAndUseFlagIsTrue(Long memberId, Long atelier);

    // TODO: QueryDSL로 성늘 최적화가 필요함
    @Query("select f from Follow f where f.member.id = :memberId and f.useFlag = true")
    Page<Follow> findAllByMemberIdAndUseFlagIsTrue(@Param("memberId") Long memberId, Pageable pageable);

    List<Follow> findFollowsByMemberIdAndUseFlagIsTrue(@Param("memberId") Long memberId);

}
