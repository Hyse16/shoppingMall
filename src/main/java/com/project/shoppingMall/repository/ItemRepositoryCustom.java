package com.project.shoppingMall.repository;

import com.project.shoppingMall.domain.Item;
import com.project.shoppingMall.dto.ItemSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {


    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDTo, Pageable pageable);
}
