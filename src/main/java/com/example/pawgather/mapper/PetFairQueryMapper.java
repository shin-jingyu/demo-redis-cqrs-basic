package com.example.pawgather.mapper;

import com.example.pawgather.controller.dto.PerFairQueryResponseDto;
import com.example.pawgather.domain.entity.PetFairRead;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetFairQueryMapper {

    PerFairQueryResponseDto.PetFairSummaryDto toDto(PetFairRead petFairRead);
}
