package com.example.pawgather.repository;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.domain.entity.QPetFairRead;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

import static com.example.pawgather.domain.constant.Constants.HOME_POSTER_IMAGE_COUNT;

@RequiredArgsConstructor
public class PetFairReadRepositoryImpl implements PetFairReadRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QPetFairRead qPetFairRead = QPetFairRead.petFairRead;

    @Override
    public List<PetFairRead> findMonthPetParis(Instant start, Instant end) {
        return queryFactory
                .selectFrom(qPetFairRead)
                .where(qPetFairRead.startDate.goe(start)
                        .and(qPetFairRead.startDate.lt(end)))
                .fetch();
    }

    @Override
    public List<PetFairRead> findMainPagePoster() {
        QPetFairRead petFair = QPetFairRead.petFairRead;

        return queryFactory
                .selectFrom(petFair)
                .orderBy(petFair.startDate.desc())
                .limit(HOME_POSTER_IMAGE_COUNT)
                .fetch();
    }

    @Override
    public List<PetFairRead> findPetFairList(PetFairSearchList searchList) {
        return queryFactory
                .select(Projections.constructor(PetFairRead.class,
                        qPetFairRead.petFairId,
                        qPetFairRead.title,
                        qPetFairRead.posterImageUrl,
                        qPetFairRead.startDate,
                        qPetFairRead.endDate,
                        qPetFairRead.simpleAddress
                ))
                .from(qPetFairRead)
                .where(
                        keywordValue(searchList.keyword()),
                        cursorCondition(searchList.cursor(), searchList.sort())
                )
                .orderBy(sortCondition(searchList.sort()))
                .limit(HOME_POSTER_IMAGE_COUNT)
                .fetch();
    }

    private BooleanExpression cursorCondition(String cursor, String sort) {
        if (cursor == null) return null;

        Instant cursorDate;
        try {
            cursorDate = Instant.parse(cursor);
        } catch (Exception e) {
            return null;
        }

        return "ASC".equalsIgnoreCase(sort)
                ? qPetFairRead.startDate.gt(cursorDate)
                : qPetFairRead.startDate.lt(cursorDate);
    }


    private OrderSpecifier<?> sortCondition(String sort) {
        return "ASC".equalsIgnoreCase(sort)
                ? qPetFairRead.startDate.asc()
                : qPetFairRead.startDate.desc();
    }

    private BooleanExpression keywordValue(String keyword) {
        return (keyword != null && !keyword.isBlank())
                ? qPetFairRead.title.containsIgnoreCase(keyword)
                : null;
    }
}
