package com.banquito.corecobros.commission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.model.ItemCommission;
import com.banquito.corecobros.commission.repository.ItemCommissionRepository;
import com.banquito.corecobros.commission.util.mapper.ItemCommissionMapper;

public class ItemCommissionServiceTest {

    @Mock
    private CommissionService commissionService;

    @Mock
    private ItemCommissionRepository itemCommissionRepository;

    @Mock
    private ItemCommissionMapper itemCommissionMapper;

    @InjectMocks
    private ItemCommissionService itemCommissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtainItemCommissionByUniqueId() {
        String uniqueId = "JVI0024966";
        ItemCommission itemCommission = new ItemCommission();
        itemCommission.setUniqueId(uniqueId);

        ItemCommissionDTO itemCommissionDTO = ItemCommissionDTO.builder()
                .uniqueId(uniqueId)
                .build();

        when(itemCommissionRepository.findByUniqueId(uniqueId)).thenReturn(Optional.of(itemCommission));
        when(itemCommissionMapper.toDTO(itemCommission)).thenReturn(itemCommissionDTO);

        List<ItemCommissionDTO> result = itemCommissionService.obtainItemCommissionByUniqueId(uniqueId);

        assertNotNull(result, "El resultado no debe ser nulo");
        assertFalse(result.isEmpty(), "La lista de resultados no debe estar vacía");
        assertEquals(1, result.size(), "La lista de resultados debe contener exactamente un elemento");
        assertEquals(uniqueId, result.get(0).getUniqueId(),
                "El uniqueId del resultado debe coincidir con el uniqueId de entrada");
    }

    @Test
    void testCreate() {
        String itemUniqueId = "JVI0024966";
        String companyUniqueId = "OJU0037961";
        String orderUniqueId = "ORD123456";
        String itemType = "TYPE_A";
        BigDecimal commissionValue = BigDecimal.TEN;
        Integer commissionId = 1; 

        ItemCommissionDTO itemCommissionDTO = ItemCommissionDTO.builder()
                .companyUniqueId(companyUniqueId)
                .itemUniqueId(itemUniqueId)
                .orderUniqueId(orderUniqueId)
                .itemType(itemType)
                .commissionValue(commissionValue)
                .build();

        Commission commission = new Commission();
        commission.setId(commissionId);
        commission.setValue(commissionValue);
        commission.setCompanyUniqueId(companyUniqueId);

        ItemCommission itemCommission = new ItemCommission();
        itemCommission.setItemUniqueId(itemUniqueId);
        itemCommission.setOrderUniqueId(orderUniqueId);
        itemCommission.setItemType(itemType);
        itemCommission.setCommissionValue(commissionValue);
        itemCommission.setCommission(commission);
        itemCommission.setCommissionId(commissionId);

        when(commissionService.getByCompanyUniqueId(companyUniqueId)).thenReturn(commission);
        when(itemCommissionMapper.toEntity(itemCommissionDTO)).thenReturn(itemCommission);
        when(itemCommissionRepository.save(itemCommission)).thenReturn(itemCommission);
        when(itemCommissionMapper.toDTO(itemCommission)).thenReturn(itemCommissionDTO);

        ItemCommissionDTO result = itemCommissionService.create(itemCommissionDTO);

        assertNotNull(result);
        assertEquals(itemUniqueId, result.getItemUniqueId());
        assertEquals(companyUniqueId, result.getCompanyUniqueId());
        assertEquals(orderUniqueId, result.getOrderUniqueId());
        assertEquals(itemType, result.getItemType());
        assertEquals(commissionId, itemCommission.getCommissionId()); 
    }

    @Test
    void testGetByItemUniqueId() {
        String itemUniqueId = "JVI0024966";

        ItemCommission itemCommission = new ItemCommission();
        itemCommission.setItemUniqueId(itemUniqueId);

        ItemCommissionDTO itemCommissionDTO = ItemCommissionDTO.builder()
                .itemUniqueId(itemUniqueId)
                .build();

        when(itemCommissionRepository.findByItemUniqueId(itemUniqueId)).thenReturn(Optional.of(itemCommission));
        when(itemCommissionMapper.toDTO(itemCommission)).thenReturn(itemCommissionDTO);

        ItemCommissionDTO result = itemCommissionService.getByItemUniqueId(itemUniqueId);

        assertNotNull(result);
        assertEquals(itemUniqueId, result.getItemUniqueId());
    }

    @Test
    void testSumTotalCommissionValueByOrderUniqueId() {
        String orderUniqueId = "GUY0045675";
        String companyUniqueId = "ZGE0000866";

        CommissionDTO commissionDTO = CommissionDTO.builder()
                .totalValue(BigDecimal.TEN)
                .build();

        when(commissionService.getCommisionDTOByCompanyUniqueId(companyUniqueId)).thenReturn(commissionDTO);
        when(itemCommissionRepository.sumCommissionValueByOrderUniqueId(orderUniqueId)).thenReturn(BigDecimal.TEN);

        CommissionDTO result = itemCommissionService.sumTotalCommissionValueByOrderUniqueId(orderUniqueId,
                companyUniqueId);

        assertNotNull(result);
        assertEquals(BigDecimal.TEN, result.getTotalValue());
    }

}
