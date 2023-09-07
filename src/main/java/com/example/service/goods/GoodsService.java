package com.example.service.goods;

import com.example.dto.goods.GoodsBoardDto;
import com.example.dto.goods.GoodsDto;
import com.example.entity.goods.Category;
import com.example.entity.goods.Goods;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.repository.CategoryRepository;
import com.example.repository.GoodsRepository;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.exception.global.GlobalErrorCode.RESOURCE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final CategoryRepository categoryRepository;
    private final MemberService memberService;

    public Long saveGoods(GoodsDto goodsDto, String memberIdx) {

        Category category = getCategory(goodsDto);

        Goods goods = Goods.createGoods(goodsDto.getName(), goodsDto.getIntroduction(), goodsDto.getLink(),
                goodsDto.getGoodsPrice(), goodsDto.getDeliveryFee(), goodsDto.getMapX(), goodsDto.getMapY(),
                goodsDto.getGoodsLimitCount(), goodsDto.getGoodsLimitTime(), memberService.getMember(memberIdx).getNickname(),
                category);

        return goodsRepository.save(goods).getId();
    }

    public GoodsBoardDto getGoodsPage(Long goodsId) {
        Goods goods = getGoods(goodsId);
        checkTime(goods);

        return new GoodsBoardDto(goods.getHostNickname(), goods.getOrderList().size() + 1,GoodsDto.toGoodsDto(goods));
    }

    public List<GoodsDto> getGoodsList() {
        List<Goods> goodsList = goodsRepository.findAll();

        goodsList.stream().forEach(goods -> checkTime(goods));

        return goodsList.stream()
                .map(GoodsDto::toGoodsDto)
                .collect(Collectors.toList());
    }

    public List<GoodsDto> getGoodsList(Long categoryId) {
        List<Goods> goodsList = goodsRepository.findAllByCategoryId(categoryId);

        goodsList.stream().forEach(goods -> checkTime(goods));

        return goodsList.stream()
                .map(GoodsDto::toGoodsDto)
                .collect(Collectors.toList());
    }

    private Goods getGoods(Long goodsId) {
        return goodsRepository.findGoodsById(goodsId)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
    }

    private Category getCategory(GoodsDto goodsDto) {
        return categoryRepository.findById(goodsDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
    }

    private void checkTime(Goods goods) {
        LocalDateTime now = LocalDateTime.now();

        if(!goods.getGoodsLimitTime().isAfter(now)) {
            goods.updateIsEnd(true);
        }
    }
}
