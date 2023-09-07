package com.example.controller;

import com.example.dto.goods.GoodsBoardDto;
import com.example.dto.goods.GoodsDto;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.service.goods.GoodsService;
import com.example.service.order.OrderService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

        //TODO: 판매자 화면 구성 -> 채팅방
        return new ResponseEntity("상품이 저장되었습니다.", OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "굿즈 상세 페이지 반환", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GoodsBoardDto.class))),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))),
    })
    @GetMapping("/goods/{goodsId}")
    public ResponseEntity getGoodsPage(@PathVariable("goodsId") Long goodsId) {
        GoodsBoardDto goodsPage = goodsService.getGoodsPage(goodsId);

        return new ResponseEntity(goodsPage, OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "굿즈 리스트 반환", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GoodsDto.class))),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))),
    })
    @GetMapping("/goods/{categoryId}")
    public ResponseEntity getGoodsList(@PathVariable("categoryId") Long categoryId) {
        List<GoodsDto> goodsList = goodsService.getGoodsList(categoryId);

        return new ResponseEntity(goodsList, OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "굿즈 리스트 반환", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GoodsDto.class))),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))),
    })
    @GetMapping("/goods")
    public ResponseEntity getGoodsList() {
        List<GoodsDto> goodsList = goodsService.getGoodsList();

        return new ResponseEntity(goodsList, OK);
    }
}
