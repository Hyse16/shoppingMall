package com.project.shoppingMall.repository;

import com.project.shoppingMall.domain.Item;
import com.project.shoppingMall.dto.ItemSearchDto;
import com.project.shoppingMall.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryCustom {


    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
