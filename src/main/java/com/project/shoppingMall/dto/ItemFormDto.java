package com.project.shoppingMall.dto;

import com.project.shoppingMall.constant.ItemSellStatus;
import com.project.shoppingMall.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    @Builder
    public ItemFormDto(Long id,String itemNm, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus) {
        this.id = id;
        this.itemNm = itemNm;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
    }

    public Item toEntity(ItemFormDto dto) {
        Item entity = Item.builder()
                .id(dto.id)
                .itemNm(dto.itemNm)
                .itemDetail(dto.itemDetail)
                .itemSellStatus(dto.itemSellStatus)
                .price(dto.price)
                .stockNumber(dto.stockNumber)
                .build();

        return entity;
    }

    public static ItemFormDto of(Item entity) {
        ItemFormDto dto = ItemFormDto.builder()
                .id(entity.getId())
                .itemNm(entity.getItemNm())
                .itemDetail(entity.getItemDetail())
                .itemSellStatus(entity.getItemSellStatus())
                .price(entity.getPrice())
                .stockNumber(entity.getStockNumber())
                .build();

        return dto;
    }

}