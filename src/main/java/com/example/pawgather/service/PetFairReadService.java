package com.example.pawgather.service;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchDate;
import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairPosterSelectLimitDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.controller.dto.PetFairReadDto;
import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.mapper.PetFairQueryMapper;
import com.example.pawgather.mapper.PetFairReadMapper;
import com.example.pawgather.repository.PetFairReadRepository;
import com.example.pawgather.repository.redis.PetFairRedisRepository;
import com.example.pawgather.usecase.PetFairReadUseCase;
import com.redis.om.spring.client.RedisModulesClient;
import com.redis.om.spring.ops.RedisModulesOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PetFairReadService implements PetFairReadUseCase {

    private final PetFairRedisRepository petFairRedisRepository;
    private final PetFairReadRepository petFairReadRepository;
    private final PetFairReadMapper petFairReadMapper;
    private final PetFairQueryMapper petFairQueryMapper;

    @Override
    public List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList) {
        return petFairReadRepository.findPetFairList(petFairSearchList);
    }

    @Override
    public PetFairReadDto readPetFairSummary(String petFairId) {
        return petFairRedisRepository.findById(petFairId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 PetFair를 찾을 수 없습니다."));
    }


    @Override
    public List<PetFairPosterSelectLimitDto> readLimitPetFairPoster() {
        List<PetFairReadDto> dtos = petFairRedisRepository.findTop10ByOrderByStartDateDesc();
        return dtos.stream()
                .map(petFairQueryMapper::toPostersDto)
                .toList();
    }

    @Override
    public List<PetFairSummaryDto> readMonthPetFairs(PetFairSearchDate searchDate) {
        List<Instant> searchMonthStartEnd = calStartEndDate(searchDate);
        Instant start = searchMonthStartEnd.get(0);
        Instant end = searchMonthStartEnd.get(1);

        return petFairReadRepository.findMonthPetParis(start,end);
    }


    private List<Instant> calStartEndDate(PetFairSearchDate searchDate) {
        YearMonth yearMonth = YearMonth.parse(searchDate.date());
        LocalDate yearMonthDay = yearMonth.atDay(1);
        Instant monthFirstDay = yearMonthDay.atStartOfDay(ZoneId.of(searchDate.zone())).toInstant();
        Instant nextMonthFirstDay = yearMonthDay.atStartOfDay(ZoneId.of(searchDate.zone())).plusMonths(1).toInstant();

        return List.of(monthFirstDay, nextMonthFirstDay);
    }
}
