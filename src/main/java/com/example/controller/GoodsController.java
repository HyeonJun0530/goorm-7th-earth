package com.example.controller;

import com.example.dto.goods.GoodsBoardDto;
import com.example.dto.goods.GoodsDto;
import com.example.service.goods.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping("/goods")
    public ResponseEntity saveGoods(@RequestBody GoodsDto goodsDto, @RequestParam String memberIdx) {
        goodsService.saveGoods(goodsDto, memberIdx);

        return new ResponseEntity("상품이 저장되었습니다.", OK);
    }

    @GetMapping("/goods/{goodsId}")
    public ResponseEntity getGoodsPage(@PathVariable("goodsId") Long goodsId) {
        GoodsBoardDto goodsPage = goodsService.getGoodsPage(goodsId);

        return new ResponseEntity(goodsPage, OK);
    }

    @GetMapping("/goods")
    public ResponseEntity getGoodsList() {
        List<GoodsDto> goodsList = goodsService.getGoodsList();

        return new ResponseEntity(goodsList, OK);
    }
}
