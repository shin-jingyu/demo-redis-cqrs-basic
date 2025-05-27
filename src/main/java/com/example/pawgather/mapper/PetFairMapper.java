package com.example.pawgather.mapper;

import com.example.pawgather.controller.dto.PetFairQueryDto.PetFairSelectLimitResponse;
import com.example.pawgather.domain.entity.PetFairRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetFairMapper {
    @Mapping(target = "id", source =  "petFairId")
    PetFairSelectLimitResponse toMainPostersResponse(PetFairRead entity);
}
