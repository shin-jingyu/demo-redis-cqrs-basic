package com.example.pawgather.controller;

import com.example.pawgather.domain.entity.PetFair;
import com.example.pawgather.repository.PetfairJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetFairQueryApi {
    private final PetfairJpaRepository petfairJpaRepository;

    @GetMapping("/v1/petfairs")
    public List<PetFair> readAllPetFairs(@RequestParam(required = false) String searchword,
                                         @RequestParam(required = false, defaultValue = "DESC") String filter) {
        List<PetFair> petfair = petfairJpaRepository.findAll();
        return petfair;
    }
}
