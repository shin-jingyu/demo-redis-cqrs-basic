package com.example.pawgather.config;

import com.example.pawgather.controller.dto.PetFairReadDto;
import com.example.pawgather.domain.entity.PetFair;
import com.example.pawgather.domain.entity.PetFairImage;
import com.example.pawgather.domain.entity.PetFairStatus;
import com.example.pawgather.mapper.PetFairReadMapper;
import com.example.pawgather.repository.PetFairJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DummyDataLoader implements ApplicationRunner {

    private final PetFairReadMapper petFairReadMapper;
    private final PetFairJpaRepository petfairJpaRepository;
//    private final PetFairReadRepository petFairReadRepository;
    private final RedisTemplate<String, PetFairReadDto> redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        if (petfairJpaRepository.count() > 0){
            log.info("repository.count: {}", petfairJpaRepository.count());
            return;
        }

        List<PetFair> dummyList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            dummyList.add(makeFair("2025 메가주 일산(상) " + i, "images/2025/05/25/" + i + ".webp", "2025-05-15T15:00:00+00:00", "2025-05-17T15:00:00+00:00", "킨텍스 2전시장", "경기도 고양시 일산서구 킨텍스로 271-59", "https://k-pet.co.kr/information/scheduled-list/2025_megazoo_spring/", "메가주 일산 설명", i));
            dummyList.add(makeFair("2025 케이펫페어 서울 " + i, "images/2025/05/26/" + (i + 10) + ".webp", "2025-08-13T15:00:00+00:00", "2025-08-16T15:00:00+00:00", "코엑스 D홀", "서울 강남구 삼성동", "https://k-pet.co.kr/information/scheduled-list/2025_kpet_seoul/", "서울 설명", i + 10));
            dummyList.add(makeFair("2025 케이펫페어 마곡 " + i, "images/2025/05/26/" + (i + 10) + ".webp", "2025-06-13T15:00:00+00:00", "2025-06-15T15:00:00+00:00", "코엑스 마곡", "서울 강서구 마곡동", "https://k-pet.co.kr/information/scheduled-list/2025_kpet_magok/", "마곡 설명", i + 10));
            dummyList.add(makeFair("2025 케이펫페어 대구 " + i, "images/2025/05/26/" + (i + 10) + ".webp", "2025-08-27T15:00:00+00:00", "2025-08-30T15:00:00+00:00", "엑스코 서관", "대구 북구", "https://k-pet.co.kr/information/scheduled-list/2025_kpet_deagu/", "대구 설명", i + 10));
            dummyList.add(makeFair("2025 케이펫페어 수원 " + i, "images/2025/05/26/" + (i + 10) + ".webp", "2025-07-04T15:00:00+00:00", "2025-07-06T15:00:00+00:00", "수원메쎄", "수원시 권선구", "https://k-pet.co.kr/information/scheduled-list/2025_kpet_suwon2/", "수원 설명", i + 10));
            dummyList.add(makeFair("2025 케이펫페어 청주 " + i, "images/2025/05/26/" + (i + 10) + ".webp", "2025-07-25T15:00:00+00:00", "2025-07-27T15:00:00+00:00", "청주오스코", "청주시 흥덕구", "https://k-pet.co.kr/information/scheduled-list/2025_kpet_cheongju/", "청주 설명", i + 10));
        }

        petfairJpaRepository.saveAll(dummyList);
        log.info("dummyList.size: {}", dummyList.size());

//        List<PetFairRead> readList = dummyList.stream()
//                .map(this::makePetFairRead)
//                .toList();
//
//        petFairReadRepository.saveAll(readList);
//        log.info("readList: {}", readList.size());
        List<PetFairReadDto> dtoList = new ArrayList<>();
        dummyList.forEach(fair -> {
            PetFairReadDto dto = petFairReadMapper.toDto(fair);
            dtoList.add(dto);
            redisTemplate.opsForValue().set("petfair:" + fair.getPetFairId(), dto);
        });
        redisTemplate.opsForValue().set("petfair:list", dtoList);
    }

    private PetFair makeFair(String title, String posterUrl, String start, String end, String sAddr, String dAddr, String url, String content, int imageIndex) {
        PetFair fair = PetFair.builder()
                .title(title)
                .posterImageUrl(posterUrl)
                .startDate(Instant.parse(start))
                .endDate(Instant.parse(end))
                .simpleAddress(sAddr)
                .detailAddress(dAddr)
                .petFairUrl(url)
                .content(content)
                .mapUrl("37.514575,127.063287")
                .telNumber("02-6121-6247")
                .status(PetFairStatus.ACTIVE)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        for (int sort = 1; sort <= 5; sort++) {
            PetFairImage img = PetFairImage.builder()
                    .imageUrl("images/content/2025/05/26/" + imageIndex + "_" + sort + ".webp")
                    .sortOrder(sort)
                    .createdAt(Instant.now())
                    .build();

            fair.addImage(img);
        }

        return fair;
    }

//    private PetFairRead makePetFairRead(PetFair fair) {
//        List<String> imageList = fair.getPairImages().stream()
//                .map(img -> img.getImageUrl() + "_" + img.getSortOrder())
//                .toList();
//        return PetFairRead.builder()
//                .title(fair.getTitle())
//                .posterImageUrl(fair.getPosterImageUrl())
//                .startDate(fair.getStartDate())
//                .endDate(fair.getEndDate())
//                .simpleAddress(fair.getSimpleAddress())
//                .detailAddress(fair.getDetailAddress())
//                .petFairUrl(fair.getPetFairUrl())
//                .content(fair.getContent())
//                .mapUrl(fair.getMapUrl())
//                .telNumber(fair.getTelNumber())
//                .status(fair.getStatus())
//                .createdAt(fair.getCreatedAt())
//                .updatedAt(fair.getUpdatedAt())
//                .images(imageList)
//                .build();
//    }
}
