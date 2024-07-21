package com.banquito.corecobros.commission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banquito.corecobros.commision.util.mapper.ItemCommissionMapper;
import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.model.ItemCommission;
import com.banquito.corecobros.commission.repository.ItemCommissionRepository;

@Service
public class ItemCommissionService {

    private final ItemCommissionRepository itemCommissionRepository;	
    private final ItemCommissionMapper itemCommissionMapper;
    
    public ItemCommissionService(ItemCommissionRepository itemCommissionRepository,
            ItemCommissionMapper itemCommissionMapper) {
        this.itemCommissionRepository = itemCommissionRepository;
        this.itemCommissionMapper = itemCommissionMapper;
    }

    public List<ItemCommissionDTO> obtainItemCommissionByUniqueId(String uniqueId) {
        List<ItemCommission> itemCommission = itemCommissionRepository.findtemCommissionByUniqueId(uniqueId);	
        return itemCommission.stream()
                .map(itemCommissionMapper::toDTO)
                .collect(Collectors.toList());
    }


    



    
}
