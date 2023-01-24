package com.example.cafe.service;

import com.example.cafe.domain.PointHistory;
import com.example.cafe.domain.PointType;
import com.example.cafe.dto.MemberDto;
import com.example.cafe.dto.requestdto.PointRequestDto;
import com.example.cafe.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointHistoryRepository pointHistoryRepository;

    // 포인트 충전 이력
    @Transactional
    public void chargePointHistory(MemberDto memberDto, PointRequestDto pointRequestDto){

        PointHistory pointHistory = PointHistory.builder()
                .point(pointRequestDto.getPoint())
                .type(PointType.POINT_CHARGE)
                .member(memberDto.toEntity())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    // 포인트 사용 이력
    @Transactional
    public void payPointHistory(MemberDto memberDto, Long totalPrice){

        PointHistory pointHistory = PointHistory.builder()
                .point(totalPrice)
                .type(PointType.POINT_PAYMENT)
                .member(memberDto.toEntity())
                .build();
        pointHistoryRepository.save(pointHistory);

    }
}
