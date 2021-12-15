package com.dayz.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = {"address", "atelier"})
    Optional<Member> findById(@Param("id") Long id);

    @Query("select m from Member m join fetch m.permission where m.username = :username")
    Optional<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m join fetch m.permission  where m.provider = :provider and m.providerId = :providerId")
    Optional<Member> findByProviderAndProviderId(@Param("provider") String provider, @Param("providerId") String providerId);

    @Query("select m from Member m where m.id = :memberId and m.useFlag = true")
    Optional<Member> findMemberByIdAndUseFlagIsTrue(@Param("memberId") Long memberId);

}