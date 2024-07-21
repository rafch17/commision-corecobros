package com.banquito.corecobros.commission.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.corecobros.commission.model.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Long> {
    Optional<Commission> findByUniqueId(String uniqueId);
}
