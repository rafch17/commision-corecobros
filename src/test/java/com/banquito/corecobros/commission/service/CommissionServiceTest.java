package com.banquito.corecobros.commission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.repository.CommissionRepository;
import com.banquito.corecobros.commission.util.mapper.CommissionMapper;
import com.banquito.corecobros.commission.util.uniqueId.UniqueIdGeneration;

public class CommissionServiceTest {

    @Mock
    private CommissionRepository commissionRepository;

    @Mock
    private CommissionMapper commissionMapper;

    @InjectMocks
    private CommissionService commissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByCompanyUniqueId() {
        String companyUniqueId = "OJU0037961";
        Commission commission = new Commission();
        commission.setCompanyUniqueId(companyUniqueId);

        when(commissionRepository.findByCompanyUniqueId(companyUniqueId)).thenReturn(Optional.of(commission));

        Commission result = commissionService.getByCompanyUniqueId(companyUniqueId);

        assertNotNull(result);
        assertEquals(companyUniqueId, result.getCompanyUniqueId());
    }

    @Test
    void testGetCommisionDTOByCompanyUniqueId() {
        String companyUniqueId = "OJU0037961";
        Commission commission = mock(Commission.class);
        CommissionDTO commissionDTO = mock(CommissionDTO.class);

        when(commissionRepository.findByCompanyUniqueId(companyUniqueId)).thenReturn(Optional.of(commission));
        when(commissionMapper.toDTO(commission)).thenReturn(commissionDTO);

        CommissionDTO result = commissionService.getCommisionDTOByCompanyUniqueId(companyUniqueId);

        assertNotNull(result);
        verify(commissionRepository).findByCompanyUniqueId(companyUniqueId);
        verify(commissionMapper).toDTO(commission);
        assertEquals(commissionDTO, result);
    }

    @Test
    public void testObtainCommissionsByItemCommissionUniqueId() {
        String uniqueId = "RQU0044777";
        List<Commission> commissions = Arrays.asList(new Commission(), new Commission());

        when(commissionRepository.findByItemCommissionUniqueId(uniqueId)).thenReturn(commissions);

        List<Commission> result = commissionService.obtainCommissionsByItemCommissionUniqueId(uniqueId);

        assertEquals(commissions, result);
    }

    @Test
    public void testObtainCommissionsByItemCommissionId() {
        String id = "6";
        List<Commission> commissions = Arrays.asList(new Commission(), new Commission());

        when(commissionRepository.findByItemCommissionId(id)).thenReturn(commissions);

        List<Commission> result = commissionService.obtainCommissionsByItemCommissionId(id);

        assertEquals(commissions, result);
    }

    @Test
    void testCreateCommission() {
        CommissionDTO commissionDTO = mock(CommissionDTO.class);
        Commission commission = mock(Commission.class);
        Commission savedCommission = mock(Commission.class);
        CommissionDTO savedCommissionDTO = mock(CommissionDTO.class);
        UniqueIdGeneration uniqueIdGenerator = mock(UniqueIdGeneration.class);

        when(commissionMapper.toEntity(commissionDTO)).thenReturn(commission);
        when(commissionRepository.findByUniqueId(anyString())).thenReturn(Optional.empty());
        when(commissionRepository.save(commission)).thenReturn(savedCommission);
        when(commissionMapper.toDTO(savedCommission)).thenReturn(savedCommissionDTO);
        when(uniqueIdGenerator.getUniqueId()).thenReturn("UNIQUE_ID");

        CommissionDTO result = commissionService.create(commissionDTO);

        assertNotNull(result);
        assertEquals(savedCommissionDTO, result);
        verify(commissionMapper).toEntity(commissionDTO);
        verify(commissionRepository).findByUniqueId(anyString());
        verify(commissionRepository).save(commission);
        verify(commissionMapper).toDTO(savedCommission);
    }

    @Test
    void testGetAllCommissions() {
        Commission commission = new Commission();
        List<Commission> commissions = Collections.singletonList(commission);

        when(commissionRepository.findAll()).thenReturn(commissions);

        List<Commission> result = commissionService.getAllCommissions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(commission, result.get(0));
    }

}
