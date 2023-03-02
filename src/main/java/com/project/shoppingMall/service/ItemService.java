package com.project.shoppingMall.service;

import com.project.shoppingMall.domain.Item;
import com.project.shoppingMall.domain.ItemImg;
import com.project.shoppingMall.dto.ItemFormDto;
import com.project.shoppingMall.repository.ItemImgRepository;
import com.project.shoppingMall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;
    private final ItemRepository itemRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList)throws Exception {
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
            ItemImg itemImg = ItemImg.builder()
                    .item(item)
                    .repimgYn(i == 0 ? "Y" : "N")
                    .build();

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }


}
