package com.example.cafe.dto.reponsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderResponseDto {

    private Long memberId;
    private Long point;
}
