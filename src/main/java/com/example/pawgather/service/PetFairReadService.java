package com.example.pawgather.service;

import com.example.pawgather.controller.dto.PetFairQueryDto.PetFairSelectLimitResponse;
import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.mapper.PetFairMapper;
import com.example.pawgather.repository.PetFairReadRepository;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetFairReadService implements PetFairReadUseCase {
    private final PetFairReadRepository petFairReadRepository;
    private final PetFairMapper mapper;

    @Override
    public List<PetFairRead> readAllPetFair() {
        return petFairReadRepository.findAll();
    }

    @Override
    public List<PetFairSelectLimitResponse> readLimitPetFairPoster() {
        List<PetFairRead> petFairReads = petFairReadRepository.findMainPagePoster();

        return petFairReads.stream()
                .map(mapper::toMainPostersResponse)
                .toList();
    }
}
