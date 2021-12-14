package com.dayz.follow.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f where f.member.id = :memberId and f.atelier.id = :atelierId")
    Follow findByMemberIdAndAtelierId(Long memberId, Long atelierId);

}
