package com.example.cafe.service;

import com.example.cafe.domain.Member;
import com.example.cafe.domain.PointHistory;
import com.example.cafe.domain.PointType;
import com.example.cafe.dto.reponsedto.PointResponseDto;
import com.example.cafe.dto.requestdto.PointRequestDto;
import com.example.cafe.repository.MemberRepository;
import com.example.cafe.repository.PointHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @DisplayName("멤버 생성 테스트")
    @Test
    void createMember(){

        // given

        Member member = Member.builder()
                .point(0L)
                .build();

        when(memberRepository.save(any())).thenReturn(member);

        // when

        String response = memberService.createMember();

        // then

        assertThat(response).isEqualTo("회원가입 완료!");
    }

    @DisplayName("포인트 충전 테스트 ")
    @Test
    void chargePoint(){

        // given
        Long memberId = 1L;
        Long point = 10000L;

        PointRequestDto pointRequestDto = PointRequestDto.builder()
                .memberId(memberId)
                .point(point)
                .build();

        Member member = Member.builder()
                .id(memberId)
                .point(0L)
                .build();

        PointHistory pointHistory = PointHistory.builder()
                .point(point)
                .type(PointType.POINT_CHARGE)
                .member(member)
                .build();

        when(memberRepository.findById(memberId)).thenReturn(Optional.ofNullable(member));
        when(pointHistoryRepository.save(any())).thenReturn(pointHistory);

        // when

        PointResponseDto response = memberService.chargePoint(pointRequestDto);

        // then

        assertThat(member.getPoint()).isEqualTo(point);
        assertThat(response.getPoint()).isEqualTo(point);


    }

}