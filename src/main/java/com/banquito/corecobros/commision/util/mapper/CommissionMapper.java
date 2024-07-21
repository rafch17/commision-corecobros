package com.banquito.corecobros.commision.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.banquito.corecobros.commission.dto.CommissionDTO;
import com.banquito.corecobros.commission.model.Commission;

@Mapper
public interface CommissionMapper {
    CommissionMapper INSTANCE = Mappers.getMapper(CommissionMapper.class);

    @Mapping(source = "commission.uniqueId", target = "uniqueId")
    CommissionDTO toDTO(Commission commission);

    @Mapping(source = "uniqueId", target = "commission.uniqueId")
    Commission toEntity(CommissionDTO commissionDTO);
}
