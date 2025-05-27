package com.example.pawgather.repository;

import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.domain.entity.QPetFairRead;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.example.pawgather.domain.constant.Constants.HOME_POSTER_IMAGE_COUNT;

public class PetFairReadRepositoryImpl implements PetFairReadRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PetFairReadRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
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
}
