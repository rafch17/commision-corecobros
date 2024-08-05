package com.banquito.corecobros.commission.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banquito.corecobros.commission.model.ItemCommission;

@Repository
public interface ItemCommissionRepository extends JpaRepository<ItemCommission, Integer> {

    Optional<ItemCommission> findByUniqueId(String uniqueId);

    Optional<ItemCommission> findByItemUniqueId(String itemUniqueId);

    @Query("SELECT SUM(ic.commissionValue) FROM ItemCommission ic WHERE ic.orderUniqueId = :orderUniqueId")
    BigDecimal sumCommissionValueByOrderUniqueId(String orderUniqueId);

}
