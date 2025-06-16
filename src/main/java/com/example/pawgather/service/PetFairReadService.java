package com.example.pawgather.service;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchDate;
import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairPosterSelectLimitDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.controller.dto.PetFairReadDto;
import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.mapper.PetFairQueryMapper;
import com.example.pawgather.repository.PetFairReadRepository;
import com.example.pawgather.repository.redis.PetFairRedisRepository;
import com.example.pawgather.usecase.PetFairReadUseCase;
import com.redis.om.spring.client.RedisModulesClient;
import com.redis.om.spring.ops.RedisModulesOperations;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetFairReadService implements PetFairReadUseCase {

    private final PetFairReadRepository petFairReadRepository;
    private final PetFairRedisRepository petFairRedisRepository;
    private final PetFairQueryMapper petFairQueryMapper;

    @Override
    public List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList) {

        Instant cursor = petFairSearchList.cursor() != null
                ? Instant.ofEpochMilli(Long.parseLong(petFairSearchList.cursor()))
                : Instant.now();
//        int end = cursor + 10;

        Sort sort = Sort.by(Sort.Direction.DESC, "startDate");
        if ("ASC".equalsIgnoreCase(petFairSearchList.sort())) {
            Sort.by(Sort.Direction.ASC, "startDate");
        }

        String keyword = petFairSearchList.keyword();

//        Pageable pageable = PageRequest.of(end, 10, sort);

//        List<PetFairReadDto> readDtoList = petFairRedisRepository
//                .findByTitleContaining(keyword, pageable)
//                .getContent();

//        return readDtoList.stream()
//                .map(petFairQueryMapper::toDto)
//                .toList();
        return null;
    }

    @Override
    public PetFairReadDto readPetFairSummary(Long petFairId) {
        String id = petFairId.toString();

        return petFairRedisRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 PetFair를 찾을 수 없습니다."));
    }


    @Override
    public List<PetFairReadDto> readLimitPetFairPoster() {
        return petFairRedisRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "startDate")
                ).stream()
                .limit(10)
                .toList();
    }

    @Override
    public List<PetFairDetailDto> readMonthPetFairs(PetFairSearchDate searchDate) {
        List<Instant> dates = new ArrayList<>();

        List<Instant> searchMonthStartEnd = calStartEndDate(searchDate, dates);
        Instant start = searchMonthStartEnd.getFirst();
        Instant end = searchMonthStartEnd.getLast();

        List<PetFairRead> result = petFairReadRepository.findMonthPetParis(start, end);

        return result.stream()
                .map(petFairQueryMapper::toDetailDto)
                .toList();
    }

    private List<Instant> calStartEndDate(PetFairSearchDate searchDate, List<Instant> dates) {
        YearMonth yearMonth = YearMonth.parse(searchDate.date());
        LocalDate yearMonthDay = yearMonth.atDay(1);
        Instant monthFirstDay = yearMonthDay.atStartOfDay(ZoneId.of(searchDate.zone())).toInstant();
        Instant nextMonthFirstDay = yearMonthDay.atStartOfDay(ZoneId.of(searchDate.zone())).plusMonths(1).toInstant();

        dates.add(monthFirstDay);
        dates.add(nextMonthFirstDay);

        return dates;
    }
}
