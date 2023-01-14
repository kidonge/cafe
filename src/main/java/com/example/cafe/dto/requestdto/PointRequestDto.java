package com.example.cafe.dto.requestdto;

import com.example.cafe.domain.PointType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PointRequestDto {

    private Integer memberId;
    private PointType type;
    private Integer point;
}
