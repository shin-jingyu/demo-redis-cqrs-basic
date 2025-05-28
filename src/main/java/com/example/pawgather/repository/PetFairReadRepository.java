package com.example.pawgather.repository;

import com.example.pawgather.domain.entity.PetFairRead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetFairReadRepository extends JpaRepository<PetFairRead, Long>, PetFairReadRepositoryCustom {
}
