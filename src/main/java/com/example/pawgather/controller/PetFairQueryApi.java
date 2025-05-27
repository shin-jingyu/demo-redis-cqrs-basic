package com.example.pawgather.controller;

import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
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
    private final PetFairReadUseCase petFairReadUseCase;

    @GetMapping("/v1/petfairs")
    public List<PetFairRead> readAllPetFairs(@RequestParam(required = false) String searchword,
                                             @RequestParam(required = false, defaultValue = "DESC") String filter) {
        return petFairReadUseCase.readAllPetFair();
    }

    @GetMapping("/v1/home")
    public List<PetFairSelectLimitResponse> readPetFairPosterTop10() {
        return petFairReadUseCase.readLimitPetFairPoster();
    }
}
