package com.project.shoppingMall.dto;

import com.project.shoppingMall.constant.ItemSellStatus;
import lombok.Data;

@Data
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}
