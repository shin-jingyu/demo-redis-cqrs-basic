package com.example.pawgather.repository.redis;

import com.example.pawgather.controller.dto.PetFairReadDto;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface PetFairRedisRepository extends RedisDocumentRepository<PetFairReadDto, String> {

}
