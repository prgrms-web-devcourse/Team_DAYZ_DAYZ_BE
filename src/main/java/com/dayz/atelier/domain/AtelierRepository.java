package com.dayz.atelier.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtelierRepository extends JpaRepository<Atelier, Long> {

    boolean existsAtelierByName(String name);

}
