package com.dayz.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.cityId = :cityId and a.regionId = :regionId and a.useFlag = true")
    Optional<Address> findByCityIdAndRegionId(@Param("cityId") Long cityId, @Param("regionId") Long regionId);

}
