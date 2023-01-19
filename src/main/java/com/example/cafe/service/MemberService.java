package com.example.cafe.service;

import com.example.cafe.domain.Member;
import com.example.cafe.domain.PointHistory;
import com.example.cafe.domain.PointType;
import com.example.cafe.dto.reponsedto.PointResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.MenuRequestDto;
import com.example.cafe.dto.requestdto.PointRequestDto;
import com.example.cafe.repository.MemberRepository;
import com.example.cafe.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public String createUser() {
        Member member = Member.builder()
                .point(0L)
                .build();

        memberRepository.save(member);

        return "회원가입 완료!";
    }

    // 포인트 충전하기 // -> 애초에 충전만을 위한 api인데 굳이 충전 필드가 들어갈 필요가 있을까?? 고민해보기
    @Transactional
    public PointResponseDto chargePoint(PointRequestDto requestDto){

        Member member = memberRepository.findById(requestDto.getMemberId()).get();

        // 멤버가 가지고 있는 포인트 변경 및 포인트 충전 이력 저장
            member.update(member.getPoint() + requestDto.getPoint());
            PointHistory pointHistory = PointHistory.builder()
                    .point(requestDto.getPoint())
                    .type(PointType.POINT_CHARGE)
                    .member(member)
                    .build();
            pointHistoryRepository.save(pointHistory);


        return PointResponseDto.builder()
                .memberId(requestDto.getMemberId())
                .point(member.getPoint())
                .build();
    }


}


// 충전하기, 결제하기를 따로 빼서 -> memberservice에 포인트 충전하기에 충전하기 넣고 ,OrderService에 Ordermenu()에 결제하기 넣고