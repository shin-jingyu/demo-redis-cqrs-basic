package com.example.pawgather.mapper;

import com.example.pawgather.controller.dto.PerFairQueryResponseDto;
import com.example.pawgather.controller.dto.PetFairReadDto;
import com.example.pawgather.domain.entity.PetFair;
import com.example.pawgather.domain.entity.PetFairImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PetFairReadMapper {

    @Mapping(target = "images", expression = "java(toImageUrls(entity))")
    PetFairReadDto toDto(PetFair entity);

    default List<String> toImageUrls(PetFair entity) {
        return entity.getPairImages().stream()
                .sorted(Comparator.comparing(PetFairImage::getSortOrder)) // 순서 보장
                .map(PetFairImage::getImageUrl)
                .toList();
    }

    PerFairQueryResponseDto.PetFairSummaryDto toSummaryDto(PetFairReadDto petFairReadDto);

}
