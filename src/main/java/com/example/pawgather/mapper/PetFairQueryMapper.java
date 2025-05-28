package com.example.pawgather.mapper;

import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.domain.entity.PetFairRead;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetFairQueryMapper {

    PetFairSummaryDto toDto(PetFairRead petFairRead);
    PetFairDetailDto toDetailDto(PetFairRead petFairRead);
}
