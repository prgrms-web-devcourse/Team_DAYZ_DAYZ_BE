package com.dayz.follow.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f where f.member.id = :memberId and f.atelier.id = :atelierId")
    Follow findByMemberIdAndAtelierId(Long memberId, Long atelierId);

    boolean existsByMemberIdAndAtelierId(Long memberId, Long atelier);

    @Query("select f from Follow f where f.member.id = :memberId and f.useFlag = true")
    Page<Follow> findAllByMemberIdAndUseFlagIsTrue(Long memberId, Pageable pageable);

}