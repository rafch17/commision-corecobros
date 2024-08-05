package com.banquito.corecobros.commission.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.banquito.corecobros.commission.model.Commission;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Optional<Commission> findByUniqueId(String uniqueId);

    @Query("SELECT c FROM Commission c JOIN c.commissions ic WHERE ic.uniqueId = :uniqueId")
    List<Commission> findByItemCommissionUniqueId(String uniqueId);

    Optional<Commission> findByCompanyUniqueId(String companyUniqueId);

}
