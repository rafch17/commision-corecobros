package com.banquito.corecobros.commission.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.model.ItemCommission;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemCommissionMapper {

    ItemCommissionDTO toDTO(ItemCommission itemCommission);

    @Mapping(target = "commission", ignore = true)
    ItemCommission toEntity(ItemCommissionDTO itemCommissionDTO);

}
