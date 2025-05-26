package com.example.pawgather.repository;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.domain.entity.PetFairRead;
import com.example.pawgather.domain.entity.QPetFairRead;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PatFairReadRepositoryImpl implements PetFairReadRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    QPetFairRead qPetFairRead = QPetFairRead.petFairRead;

    @Override
    public List<PetFairRead> findList(PetFairSearchList searchList) {
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
                .limit(10)
                .fetch();
    }

    private BooleanExpression cursorCondition(String cursor, String sort) {
        if (cursor == null) return null;

        Long cursorId = Long.parseLong(cursor);

        // sort가 ASC인 경우 → id가 더 큰 걸 가져와야 오래된 순으로 다음 페이지
        if ("ASC".equalsIgnoreCase(sort)) {
            return qPetFairRead.petFairId.gt(cursorId); // 오래된 순
        } else {
            return qPetFairRead.petFairId.lt(cursorId); // 최신순
        }
    }


    private OrderSpecifier<?> sortCondition(String sort) {
        if ("ASC".equalsIgnoreCase(sort)) {
            return qPetFairRead.startDate.asc(); // 오래된 순
        } else {
            return qPetFairRead.startDate.desc(); // 최신순 (기본)
        }
    }
    private BooleanExpression keywordValue(String keyword) {
        return keyword == null ? null : qPetFairRead.title.containsIgnoreCase(keyword);
    }
}
