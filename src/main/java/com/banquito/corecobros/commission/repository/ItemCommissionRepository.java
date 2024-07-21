package com.banquito.corecobros.commission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.corecobros.commission.model.ItemCommission;

public interface ItemCommissionRepository extends JpaRepository<ItemCommission, Long> {
    List<ItemCommission> findByUniqueIdItemCommission_Commission(String uniqueId);
}


