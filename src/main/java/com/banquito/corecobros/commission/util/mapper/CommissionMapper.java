package com.banquito.corecobros.commission.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommissionMapper {

    CommissionDTO toDTO(Commission commission);

    Commission toEntity(CommissionDTO commissionDTO);

}
