package com.example.cafe.controller;

import com.example.cafe.dto.reponsedto.PointResponseDto;
import com.example.cafe.dto.reponsedto.ResponseDto;
import com.example.cafe.dto.requestdto.MenuRequestDto;
import com.example.cafe.dto.requestdto.PointRequestDto;
import com.example.cafe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseDto<String> createUser(){
        return memberService.createUser();
    }

    @PostMapping("/point/charge")
    public ResponseDto<PointResponseDto> chargePoint(@RequestBody PointRequestDto requestDto){
        return memberService.chargePoint(requestDto);
    }


}
