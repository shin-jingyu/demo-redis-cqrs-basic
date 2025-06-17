package com.example.pawgather.mapper;

import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairPosterSelectLimitDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.controller.dto.PetFairReadDto;
import com.example.pawgather.domain.entity.PetFairRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetFairQueryMapper {

    PetFairSummaryDto toDto(PetFairReadDto petFairRead);
    PetFairDetailDto toDetailDto(PetFairRead petFairRead);

    @Mapping(target = "id", source =  "petFairId")
    PetFairPosterSelectLimitDto toMainPostersDto(PetFairRead entity);
    PetFairPosterSelectLimitDto toPostersDto(PetFairReadDto petFairReadDto);
}
