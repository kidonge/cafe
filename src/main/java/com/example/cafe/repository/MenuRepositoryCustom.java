package com.example.cafe.repository;

import com.example.cafe.dto.reponsedto.PopularMenuDto;

import java.util.List;

public interface MenuRepositoryCustom {

    List<PopularMenuDto> popularMenus();
}
