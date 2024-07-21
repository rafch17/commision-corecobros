package com.banquito.corecobros.commission.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ItemCommissionDTO {

    private Long id;
    private Long commissionId;
    private String uniqueId;
    private String itemId;
    private String itemType;
    private BigDecimal commissionValue;

}
