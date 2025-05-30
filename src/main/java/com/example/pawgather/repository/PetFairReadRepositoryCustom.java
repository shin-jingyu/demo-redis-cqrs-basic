package com.example.pawgather.repository;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.domain.entity.PetFairRead;

import java.time.Instant;
import java.util.List;

public interface PetFairReadRepositoryCustom {
    List<PetFairRead> findPetFairList(PetFairSearchList searchList);
    List<PetFairRead> findMainPagePoster();
    List<PetFairRead> findMonthPetParis(Instant start, Instant end);
}
