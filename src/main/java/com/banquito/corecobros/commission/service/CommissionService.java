package com.banquito.corecobros.commission.service;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.banquito.corecobros.commission.util.mapper.CommissionMapper;
import com.banquito.corecobros.commission.util.uniqueId.UniqueIdGeneration;

import lombok.extern.slf4j.Slf4j;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.repository.CommissionRepository;

import java.util.List;

@Slf4j
@Service
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final CommissionMapper commissionMapper;

    public CommissionService(CommissionRepository commissionRepository, CommissionMapper commissionMapper) {
        this.commissionRepository = commissionRepository;
        this.commissionMapper = commissionMapper;
    }

    public Optional<CommissionDTO> obtainCommissionByUniqueId(String uniqueId) {
        Optional<Commission> commission = commissionRepository.findByUniqueId(uniqueId);
        return commission.map(commissionMapper::toDTO);
    }

    public Commission getByCompanyUniqueId(String companyUniqueId) {
        return commissionRepository.findByCompanyUniqueId(companyUniqueId).orElseThrow(() -> {
            log.error("Commission not found for companyUniqueId {}", companyUniqueId);
            throw new RuntimeException("Commission not found for companyUniqueId " + companyUniqueId);
        });
    }

    public CommissionDTO getCommisionDTOByCompanyUniqueId(String companyUniqueId) {
        Commission commission = getByCompanyUniqueId(companyUniqueId);
        return commissionMapper.toDTO(commission);
    }
    

    
    public List<Commission> obtainCommissionsByItemCommissionUniqueId(String uniqueId) {
        return commissionRepository.findByItemCommissionUniqueId(uniqueId);
    }

        
    public List<Commission> obtainCommissionsByItemCommissionId(String id) {
        return commissionRepository.findByItemCommissionId(id);
    }

    public CommissionDTO create(CommissionDTO commissionDTO) {
        UniqueIdGeneration uniqueIdGenerator = new UniqueIdGeneration();
        String uniqueId;
        boolean uniqueIdExists;

        do {
            uniqueId = uniqueIdGenerator.getUniqueId();
            uniqueIdExists = commissionRepository.findByUniqueId(uniqueId).isPresent();
        } while (uniqueIdExists);

        Commission commission = commissionMapper.toEntity(commissionDTO);
        commission.setUniqueId(uniqueId);
        Commission savedCommission = commissionRepository.save(commission);
        return commissionMapper.toDTO(savedCommission);
    }

    public List<Commission> getAllCommissions() {
        return commissionRepository.findAll();
    }

}
