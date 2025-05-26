package com.example.pawgather.repository;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.domain.entity.PetFairRead;

import java.util.List;

public interface PetFairReadRepositoryCustom {
    List<PetFairRead> findList(PetFairSearchList searchList);
}
