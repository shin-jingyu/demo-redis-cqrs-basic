package com.example.pawgather.controller.dto;

import com.example.pawgather.domain.entity.PetFairStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.redis.om.spring.annotations.Document;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;


import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Document
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetFairReadDto implements Serializable {

    @Id
    private String id;

    @Indexed
    private String title;

    private String posterImageUrl;

    @Indexed
    private Instant startDate;

    @Indexed
    private Instant endDate;

    private String simpleAddress;
    private String detailAddress;
    private String petFairUrl;
    private String content;
    private String mapUrl;
    private String telNumber;
    private PetFairStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<String> images;
}
