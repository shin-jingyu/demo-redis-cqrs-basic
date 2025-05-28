package com.example.pawgather.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "pet_fair_read")
@Builder
public class PetFairRead {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petFairId;
    @Column(nullable = false)
    private String title;
    private String posterImageUrl;
    @Column(nullable = false)
    private Instant startDate;
    @Column(nullable = false)
    private Instant endDate;
    private String simpleAddress;
    private String detailAddress;
    @Lob
    private String petFairUrl;
    @Lob
    private String content;
    @Lob
    private String mapUrl;
    private String telNumber;
    @Enumerated(EnumType.STRING)
    private PetFairStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> images;

    public PetFairRead(Long petFairId, String title, String posterImageUrl,
                       Instant startDate, Instant endDate, String simpleAddress) {
        this.petFairId = petFairId;
        this.title = title;
        this.posterImageUrl = posterImageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.simpleAddress = simpleAddress;
    }
}
