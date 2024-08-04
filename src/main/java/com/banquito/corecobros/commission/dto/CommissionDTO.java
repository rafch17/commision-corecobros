package com.banquito.corecobros.commission.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CommissionDTO {

    private Long id;
    private String name;
    private String uniqueId;
    private String chargeDistribution;
    private BigDecimal value;
    private BigDecimal totalValue;
    private BigDecimal companyValue;
    private String companyUniqueId;
    private BigDecimal debtorValue;
    private String creditorAccount;

}
