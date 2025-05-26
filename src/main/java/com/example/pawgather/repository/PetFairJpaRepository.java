package com.example.pawgather.repository;

import com.example.pawgather.domain.entity.PetFair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetFairJpaRepository extends JpaRepository<PetFair, Long> {
}
