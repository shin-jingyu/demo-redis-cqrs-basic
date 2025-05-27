package com.example.pawgather.mapper;

import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDetailDto;
import com.example.pawgather.domain.entity.PetFairRead;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetFairQueryMapper {

    PetFairSummaryDto toDto(PetFairRead petFairRead);
    PetFairSummaryDetailDto toDetailDto(PetFairRead petFairRead);
}
