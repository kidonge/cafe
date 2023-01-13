package com.example.cafe.dto.reponsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Integer memberId;
    private Integer point;
}
