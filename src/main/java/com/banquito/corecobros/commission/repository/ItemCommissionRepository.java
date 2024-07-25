package com.banquito.corecobros.commission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.corecobros.commission.model.ItemCommission;
import java.util.Optional;

@Repository
public interface ItemCommissionRepository extends JpaRepository<ItemCommission, Long> {

    Optional<ItemCommission> findByUniqueId(String uniqueId);

}
