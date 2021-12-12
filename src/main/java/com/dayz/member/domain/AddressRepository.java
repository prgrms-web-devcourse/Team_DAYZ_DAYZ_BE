package com.dayz.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findAddressByCityIdAndRegionIdAndUseFlagIsTrue(Long cityId, Long regionId);

}
