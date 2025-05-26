package com.example.pawgather.controller;

import com.example.pawgather.domain.entity.PetFair;
import com.example.pawgather.repository.PetFairJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetFairCommandApi {
    private final PetFairJpaRepository petfairJpaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RedirectView createPetFair(@RequestBody PetFair petfair) {
        petfairJpaRepository.save(petfair);
        return new RedirectView("/api/v1/petfairs");
    }

    @DeleteMapping("/v1/petfairs/{petfairid}")
    public void deletePetFair(@PathVariable("petfairid") Long petFairId){
        petfairJpaRepository.deleteById(petFairId);
    }
}
