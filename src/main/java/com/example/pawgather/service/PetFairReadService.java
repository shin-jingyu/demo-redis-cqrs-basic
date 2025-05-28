package com.example.pawgather.service;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.mapper.PetFairQueryMapper;
import com.example.pawgather.repository.PetFairReadRepository;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetFairReadService implements PetFairReadUseCase {

    private final PetFairReadRepository petFairReadRepository;

    private final PetFairQueryMapper petFairQueryMapper;

    @Override
    public List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList) {

        var PetFairs = petFairReadRepository.findPetFairList(petFairSearchList);

        return PetFairs.stream()
                .map(petFairQueryMapper::toDto)
                .toList();
    }

    @Override
    public PetFairDetailDto readPetFairSummary(Long petFairId) {
        var petFairRead = petFairReadRepository.findById(petFairId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 PetFair를 찾을 수 없습니다.") );
        return petFairQueryMapper.toDetailDto(petFairRead);
    }
}
