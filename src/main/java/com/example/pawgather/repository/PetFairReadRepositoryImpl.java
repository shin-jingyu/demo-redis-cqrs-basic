package com.example.pawgather.repository;

import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.domain.entity.QPetFairRead;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

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
                .limit(10)
                .fetch();
    }
}
