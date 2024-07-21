package com.banquito.corecobros.commission.service;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.banquito.corecobros.commision.util.mapper.CommissionMapper;
import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;
import com.banquito.corecobros.commission.repository.CommissionRepository;

@Service
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final CommissionMapper commissionMapper;
   
   
    public CommissionService(CommissionRepository commissionRepository, CommissionMapper commissionMapper) {
        this.commissionRepository = commissionRepository;
        this.commissionMapper = CommissionMapper.INSTANCE;
    }

    public Optional<CommissionDTO> obtainCommissionByUniqueId(String uniqueId) {
        Optional<Commission> commission = commissionRepository.findByUniqueId(uniqueId);
        return commission.map(commissionMapper::toDTO);
    }

    public CommissionDTO saveCommission(CommissionDTO commissionDTO) {
        Commission commission = commissionMapper.toEntity(commissionDTO);
        Commission savedCommission = commissionRepository.save(commission);
        return commissionMapper.toDTO(savedCommission);
    }


    



}
