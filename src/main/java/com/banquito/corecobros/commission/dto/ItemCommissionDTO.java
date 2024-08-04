package com.banquito.corecobros.commission.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ItemCommissionDTO {

    private Long id; //no al crear
    private Long commissionId; //no al crear
    private String uniqueId; //no al crear
    private String companyUniqueId;
    private String orderUniqueId;
    private String itemUniqueId;
    private String itemType;
    private BigDecimal commissionValue; //no al crear
}
