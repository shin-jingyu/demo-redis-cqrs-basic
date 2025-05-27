package com.example.pawgather.usecase;

import com.example.pawgather.controller.dto.PetFairQueryDto.PetFairSelectLimitResponse;
import com.example.pawgather.domain.entity.PetFairRead;

import java.util.List;

public interface PetFairReadUseCase {
    List<PetFairSelectLimitResponse> readLimitPetFairPoster();

    List<PetFairRead> readAllPetFair();
}
