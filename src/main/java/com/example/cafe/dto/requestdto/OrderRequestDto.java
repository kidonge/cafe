package com.example.cafe.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderRequestDto {

    private Integer memberId;
    private Integer menuId;
    private Integer amount;

}
