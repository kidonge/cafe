package com.example.cafe.repository;

import com.example.cafe.domain.PopularMenu;
import com.example.cafe.domain.QOrderItem;
import com.example.cafe.dto.reponsedto.PopularMenuDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MenuRepositoryImpl implements MenuRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QOrderItem orderItem = QOrderItem.orderItem;


    @Override
    public List<PopularMenuDto> popularMenus() {

        LocalDate weekBefore = LocalDate.now().minusDays(7);
        LocalDate yesterday = LocalDate.now();

        return queryFactory.select(Projections.constructor(PopularMenuDto.class,
                orderItem.menuId, orderItem.amount.sum()))
                .from(orderItem)
                .where(orderItem.createdAt.between(weekBefore.atStartOfDay(), yesterday.atTime(LocalTime.MAX).withNano(0)))
                .groupBy(orderItem.menuId)
                .orderBy(orderItem.amount.sum().desc())
                .limit(3)
                .fetch();
    }
}
