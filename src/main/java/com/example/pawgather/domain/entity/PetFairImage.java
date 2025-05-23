package com.example.pawgather.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "pet_fair_image")
@Builder
public class PetFairImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petFairImageId;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_fair_id")
    private PetFair petFair;;
    @Column(nullable = false)
    private String imageUrl;
    private Integer sortOrder;
    private ZonedDateTime createdAt;
}
