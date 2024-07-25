package com.banquito.corecobros.commission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banquito.corecobros.commission.util.mapper.ItemCommissionMapper;
import com.banquito.corecobros.commission.util.uniqueId.UniqueIdGeneration;
import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.model.ItemCommission;
import com.banquito.corecobros.commission.repository.ItemCommissionRepository;
import java.util.Optional;

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
        Optional<ItemCommission> itemCommission = itemCommissionRepository.findByUniqueId(uniqueId);
        return itemCommission.stream()
                .map(itemCommissionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemCommissionDTO create(ItemCommissionDTO itemCommissionDTO) {
        UniqueIdGeneration uniqueIdGenerator = new UniqueIdGeneration();
        String uniqueId;
        boolean uniqueIdExists;

        do {
            uniqueId = uniqueIdGenerator.getUniqueId();
            uniqueIdExists = itemCommissionRepository.findByUniqueId(uniqueId).isPresent();
        } while (uniqueIdExists);

        ItemCommission itemCommission = itemCommissionMapper.toEntity(itemCommissionDTO);
        itemCommission.setUniqueId(uniqueId);
        ItemCommission savedItemCommission = itemCommissionRepository.save(itemCommission);
        return itemCommissionMapper.toDTO(savedItemCommission);
    }

}
