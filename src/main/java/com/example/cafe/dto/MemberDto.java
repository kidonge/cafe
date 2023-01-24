package com.example.cafe.dto;

import com.example.cafe.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private Long point;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .point(point)
                .build();
    }
}
