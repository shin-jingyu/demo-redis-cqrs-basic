package com.example.pawgather.domain.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
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
import org.aspectj.weaver.loadtime.definition.Definition;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pet_fair_read")
@Builder
public class PetFairRead {
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
    @Lob
    private String petFairUrl;
    @Lob
    private String content;
    @Lob
    private String mapUrl;
    private String telNumber;
    @Enumerated(EnumType.STRING)
    private PetFairStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> images;
}
