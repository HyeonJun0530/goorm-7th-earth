package com.example.controller;

import com.example.dto.goods.GoodsBoardDto;
import com.example.dto.goods.GoodsDto;
import com.example.dto.goods.GoodsImagesDto;
import com.example.dto.goods.GoodsSaveDto;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.service.goods.GoodsImagesService;
import com.example.service.goods.GoodsService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GoodsController {

    private final GoodsService goodsService;
    private final GoodsImagesService goodsImagesService;

    @PostMapping("/goods")
    public ResponseEntity saveGoods(@RequestBody GoodsSaveDto goodsSaveDto, @RequestParam String memberIdx) {
        goodsService.saveGoods(goodsSaveDto, memberIdx);

        return new ResponseEntity("상품이 저장되었습니다.", OK);
    }

    @PostMapping("/{goodsId}/image")
    public ResponseEntity saveImage(@RequestParam("goodsImgFile") List<MultipartFile> goodsImgFileList,
                                    @PathVariable Long goodsId) throws IOException {
        goodsService.saveImage(goodsImgFileList, goodsId);

        return new ResponseEntity("이미지 저장 성공", OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "굿즈 상세 페이지 반환", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GoodsBoardDto.class))),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))),
    })
    @GetMapping("/goods/{goodsId}/page")
    public ResponseEntity getGoodsPage(@PathVariable("goodsId") Long goodsId) {
        GoodsBoardDto goodsPage = goodsService.getGoodsPage(goodsId);

        return new ResponseEntity(goodsPage, OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "굿즈 리스트 반환", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GoodsDto.class))),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))),
    })
    @GetMapping("/goods/{categoryId}/list")
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

    @GetMapping("/{goodsId}/image")
    public ResponseEntity getRepGoodsImage(@PathVariable Long goodsId) {
        GoodsImagesDto repImages = goodsImagesService.getRepImages(goodsId);

        return ResponseEntity.ok().
                contentType(MediaType.IMAGE_JPEG)
                .body(repImages);
    }

    @GetMapping("/{goodsId}/images")
    public ResponseEntity getGoodImageList(@PathVariable Long goodsId) {
        List<GoodsImagesDto> goodsImageList = goodsImagesService.getGoodsImageList(goodsId);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(goodsImageList);
    }

}
