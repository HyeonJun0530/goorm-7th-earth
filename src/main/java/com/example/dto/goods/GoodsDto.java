package com.example.dto.goods;

import com.example.entity.goods.Goods;
import com.example.entity.goods.GoodsImages;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GoodsDto {

    private String name;

    private String introduction;

    private String link;

    private Integer goodsPrice;

    private Integer deliveryFee;

    private Integer mapX;

    private Integer mapY;

    private Integer goodsLimitCount;

    private LocalDateTime goodsLimitTime;

    private Long category;

    private Integer realDeliveryFee;


    public static GoodsDto toGoodsDto(Goods goods) {
        GoodsDto goodsDto = new GoodsDto();

        goodsDto.setName(goods.getName());
        goodsDto.setGoodsPrice(goods.getGoodsPrice());
        goodsDto.setIntroduction(goods.getIntroduction());
        goodsDto.setGoodsLimitCount(goods.getGoodsLimitCount());
        goodsDto.setGoodsLimitTime(goods.getGoodsLimitTime());
        goodsDto.setCategory(goods.getCategory().getId());
        goodsDto.setLink(goods.getLink());
        goodsDto.setDeliveryFee(goods.getDeliveryFee());
        goodsDto.setMapX(goodsDto.getMapX());
        goodsDto.setMapY(goodsDto.getMapY());
        goodsDto.setRealDeliveryFee(goods.getDeliveryFee() / goods.getCurrentOrderCount());
        return goodsDto;
    }
}
