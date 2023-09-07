package com.example.dto.goods;

import com.example.entity.goods.GoodsImages;
import lombok.Data;

@Data
public class GoodsImagesDto {

    private byte[] images;

    public static GoodsImagesDto toDto(GoodsImages goodsImage) {
        GoodsImagesDto goodsImagesDto = new GoodsImagesDto();
        goodsImagesDto.images = goodsImage.getImage();

        return goodsImagesDto;
    }
}
