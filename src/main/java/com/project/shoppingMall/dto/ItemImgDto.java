package com.project.shoppingMall.dto;

import com.project.shoppingMall.domain.ItemImg;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
