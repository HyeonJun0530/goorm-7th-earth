package com.example.dto.goods;

import com.example.entity.goods.Goods;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

        return goodsDto;
    }
}