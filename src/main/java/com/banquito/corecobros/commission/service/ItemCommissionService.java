package com.banquito.corecobros.commission.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.model.ItemCommission;
import com.banquito.corecobros.commission.repository.ItemCommissionRepository;
import com.banquito.corecobros.commission.util.mapper.ItemCommissionMapper;
import com.banquito.corecobros.commission.util.uniqueId.UniqueIdGeneration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemCommissionService {
    private final CommissionService commissionService;
    private final ItemCommissionRepository itemCommissionRepository;
    private final ItemCommissionMapper itemCommissionMapper;

    public ItemCommissionService(CommissionService commissionService, ItemCommissionRepository itemCommissionRepository,
            ItemCommissionMapper itemCommissionMapper) {
        this.commissionService = commissionService;
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
        Commission commission = commissionService.getByCompanyUniqueId(itemCommissionDTO.getCompanyUniqueId());
        itemCommission.setUniqueId(uniqueId);
        itemCommission.setCommission(commission);
        itemCommission.setCommissionValue(commission.getValue());
        itemCommission.setCommissionId(commission.getId());
        ItemCommission savedItemCommission = itemCommissionRepository.save(itemCommission);
        ItemCommissionDTO item =  itemCommissionMapper.toDTO(savedItemCommission);
        item.setCompanyUniqueId(commission.getCompanyUniqueId());
        return item;
    }

    public ItemCommissionDTO getByItemUniqueId(String itemUniqueId) {
        Optional<ItemCommission> itemCommission = itemCommissionRepository
                .findByItemUniqueId(itemUniqueId);
        return itemCommission.map(itemCommissionMapper::toDTO).orElseThrow(() -> {
            log.error("ItemCommission not found for itemUniqueId {}", itemUniqueId);
            throw new RuntimeException("ItemCommission not found for itemUniqueId " + itemUniqueId);
        });
    }

    public CommissionDTO sumTotalCommissionValueByOrderUniqueId(String orderUniqueId, String companyUniqueId) {
        CommissionDTO commission = commissionService.getCommisionDTOByCompanyUniqueId(companyUniqueId);
        BigDecimal totalByOrder = itemCommissionRepository.sumCommissionValueByOrderUniqueId(orderUniqueId);
        commission.setTotalValue(totalByOrder);
        return commission;
    }
}
