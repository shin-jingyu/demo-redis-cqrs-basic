package com.example.pawgather.controller;

import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.repository.PetFairReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.pawgather.controller.dto.PetFairQueryDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetFairQueryApi {
    private final PetFairReadRepository petFairReadRepository;

    @GetMapping("/v1/petfairs")
    public List<PetFairRead> readAllPetFairs(@RequestParam(required = false) String searchword,
                                             @RequestParam(required = false, defaultValue = "DESC") String filter) {
        return petFairReadRepository.findAll();
    }

    @GetMapping("/v1/home")
    @Transactional(readOnly= true)
    public List<PetFairSelectLimitResponse> readPetFairPosterTop10() {
        List<PetFairRead> petFairReads = petFairReadRepository.findMainPagePoster();
        return petFairReads.stream()
                .map(read -> new PetFairSelectLimitResponse(read.getPetFairId(), read.getPosterImageUrl()))
                .toList();
    }
}
