package com.project.shoppingMall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CartItemDto {

    @NotNull(message = "상품 아이디는 필수 입력 값입니다")
    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

    @Builder
    public CartItemDto(Long itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }
}
