package com.example.pawgather.service;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto;
import com.example.pawgather.mapper.PetFairQueryMapper;
import com.example.pawgather.repository.PetFairReadRepository;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetFairReadService implements PetFairReadUseCase {

    private final PetFairReadRepository petFairReadRepository;

    private final PetFairQueryMapper petFairQueryMapper;

    @Override
    public List<PerFairQueryResponseDto.PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList) {

        var PetFairs = petFairReadRepository.findPetFeirList(petFairSearchList);

        return PetFairs.stream()
                .map(petFairQueryMapper::toDto)
                .toList();
    }
}
