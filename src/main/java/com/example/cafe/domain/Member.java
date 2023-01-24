package com.example.cafe.domain;

import com.example.cafe.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long point;

    public void updatePoint(Long point){
        this.point = point;
    }

    public MemberDto toDto(){
        return MemberDto.builder()
                .id(id)
                .point(point)
                .build();
    }

}


