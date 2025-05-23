package com.example.pawgather.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pet_fair")
public class PetFair {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petFairId;
    @Column(nullable = false)
    private String title;
    private String posterImageUrl;
    @Column(nullable = false)
    private ZonedDateTime startDate;
    @Column(nullable = false)
    private ZonedDateTime endDate;
    private String simpleAddress;
    private String detailAddress;
    @Column(columnDefinition = "text")
    private String petFairUrl;
    @Column(columnDefinition = "text")
    private String content;
    @Lob
    private String mapUrl;
    private String telNumber;
    @Enumerated(EnumType.STRING)
    private PetFairStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @OneToMany(mappedBy="petFairImageId", cascade = CascadeType.PERSIST)
    private List<PetFairImage> pairImages = new ArrayList<>();
}
