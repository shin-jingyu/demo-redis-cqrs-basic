package com.example.pawgather.repository.redis;

import com.example.pawgather.controller.dto.PetFairReadDto;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface PetFairRedisRepository extends RedisDocumentRepository<PetFairReadDto, String> {
    List<PetFairReadDto> findByStartDateGreaterThanEqualAndStartDateLessThanOrderByStartDateDesc(Instant start, Instant end);
    List<PetFairReadDto> findTop10ByOrderByStartDateDesc();
    List<PetFairReadDto> findByTitleContaining(String keyword, Pageable pageable);
}
