package com.banquito.corecobros.commission.dto;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class CollectionPaymentRecordDTO {
    private Integer idCollection;
    private Integer itemCommissionId;
    private String uniqueId;
    private BigDecimal ammount;
    private String type;
    private LocalDate date;
    private BigDecimal outstandingBalance;
    private String channel;

}
