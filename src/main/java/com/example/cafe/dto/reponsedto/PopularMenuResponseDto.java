package com.example.cafe.dto.reponsedto;

import com.example.cafe.domain.PopularMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PopularMenuResponseDto {

    private List<PopularMenu> menuList;
}
