package com.example.pawgather.service;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchDate;
import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairPosterSelectLimitDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.mapper.PetFairQueryMapper;
import com.example.pawgather.repository.PetFairReadRepository;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
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

    private final PetFairQueryMapper petFairQueryMapper;

    @Override
    public List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList) {

        var PetFairs = petFairReadRepository.findPetFairList(petFairSearchList);

        return PetFairs.stream()
                .map(petFairQueryMapper::toDto)
                .toList();
    }

    @Override
    public PetFairDetailDto readPetFairSummary(Long petFairId) {
        var petFairRead = petFairReadRepository.findById(petFairId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 PetFair를 찾을 수 없습니다.") );
        return petFairQueryMapper.toDetailDto(petFairRead);
    }

    @Override
    public List<PetFairPosterSelectLimitDto> readLimitPetFairPoster() {
        List<PetFairRead> petFairReads = petFairReadRepository.findMainPagePoster();

        return petFairReads.stream()
                .map(petFairQueryMapper::toMainPostersDto)
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
