package com.banquito.corecobros.commision.util.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.banquito.corecobros.commission.dto.ItemCommissionDTO;
import com.banquito.corecobros.commission.model.ItemCommission;

public interface ItemCommissionMapper {
    ItemCommissionMapper INSTANCE = Mappers.getMapper(ItemCommissionMapper.class);

    @Mapping(source = "itemCommission.uniqueId", target = "uniqueId")
    ItemCommissionDTO toDTO(ItemCommission itemCommission);

    @Mapping(source = "uniqueId", target = "itemCommission.uniqueId")
    ItemCommission toEntity(ItemCommissionDTO itemCommissionDTO);




}
