package com.banquito.corecobros.commission.external.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.banquito.corecobros.commission.dto.CollectionPaymentRecordDTO;

@Service
public class CollectionPaymentRecordService {

    public List<CollectionPaymentRecordDTO> getAllByItemCollection(String uniqueId) {
        return List.of(CollectionPaymentRecordDTO.builder()
                .idCollection(1L)
                .itemCommissionId(1L)
                .uniqueId("COLPAY001")
                .ammount(BigDecimal.valueOf(20.0))
                .type("TOT")
                .date(LocalDate.of(2024, 7, 12))
                .outstandingBalance(BigDecimal.valueOf(20.0))
                .channel("VEN")
                .build());
    }

}
