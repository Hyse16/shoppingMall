package com.project.shoppingMall.dto;

import com.project.shoppingMall.domain.ItemImg;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @Builder
    public ItemImgDto(Long id,String imgName, String oriImgName, String imgUrl, String repImgYn) {
        this.id = id;
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public ItemImg toEntity(ItemImgDto dto) {
        ItemImg entity = ItemImg.builder()
                .id(dto.id)
                .imgName(dto.imgName)
                .oriImgName(dto.oriImgName)
                .imgUrl(dto.imgUrl)
                .repimgYn(dto.repImgYn)
                .build();

        return entity;
    }

    public static ItemImgDto of(ItemImg entity) {
        ItemImgDto dto = ItemImgDto.builder()
                .id(entity.getId())
                .imgName(entity.getImgName())
                .oriImgName(entity.getOriImgName())
                .imgUrl(entity.getImgUrl())
                .repImgYn(entity.getRepimgYn())
                .build();

        return dto;
    }
}
