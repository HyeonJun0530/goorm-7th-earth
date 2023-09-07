package com.example.service.goods;

import com.example.dto.goods.GoodsBoardDto;
import com.example.dto.goods.GoodsDto;
import com.example.dto.goods.GoodsSaveDto;
import com.example.entity.Member;
import com.example.entity.goods.Category;
import com.example.entity.goods.Goods;
import com.example.entity.goods.GoodsImages;
import com.example.entity.order.Order;
import com.example.entity.order.OrderType;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.repository.*;
import com.example.service.MemberService;
import com.example.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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
    private final BoardService boardService;
    private final GoodsImagesRepository goodsImagesRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long saveGoods(GoodsSaveDto goodsSaveDto, String memberIdx, List<MultipartFile> multipartFileList) throws IOException {
        GoodsDto goodsDto = goodsSaveDto.getGoodsDto();
        Category category = getCategory(goodsDto);

        Goods goods = Goods.createGoods(goodsDto.getName(), goodsDto.getIntroduction(), goodsDto.getLink(), goodsDto.getGoodsPrice(), goodsDto.getDeliveryFee(), goodsDto.getMapX(), goodsDto.getMapY(), goodsDto.getGoodsLimitCount(), goodsDto.getGoodsLimitTime(), memberService.getMember(memberIdx).getNickname(), category);

        boardService.createBoard(goodsSaveDto.getBoardContent(), goods);

        Long goodsId = goodsRepository.save(goods).getId();

        for (int i = 0; i < multipartFileList.size(); i++) {
            GoodsImages goodsImages = new GoodsImages();
            goodsImages.setGoods(goods);
            goodsImages.setImage(multipartFileList.get(i).getBytes());

            if (i == 0) goodsImages.setRepimgYn(true);
            else goodsImages.setRepimgYn(false);

            goodsImagesRepository.save(goodsImages);
        }

        return goodsId;
    }

    public GoodsBoardDto getGoodsPage(Long goodsId) {
        Goods goods = getGoods(goodsId);
        String hostNickname = goods.getHostNickname();
        Member findMember = memberRepository.findMemberByNickname(hostNickname).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        List<Order> sellOrderList = orderRepository.findOrdersByMember_IdAndOrderType(findMember.getId(), OrderType.SELL);

        checkTime(goods);

        return new GoodsBoardDto(hostNickname, sellOrderList.size(), goods.getOrderList().size() + 1, GoodsDto.toGoodsDto(goods));
    }

    public List<GoodsDto> getGoodsList() {
        List<Goods> goodsList = goodsRepository.findAll();
        goodsList.forEach(this::checkTime);


        return goodsList.stream().map(GoodsDto::toGoodsDto).collect(Collectors.toList());
    }

    public List<GoodsDto> getGoodsList(Long categoryId) {
        List<Goods> goodsList = goodsRepository.findAllByCategoryId(categoryId);
        goodsList.forEach(this::checkTime);


        return goodsList.stream().map(GoodsDto::toGoodsDto).collect(Collectors.toList());
    }

    public Goods getGoods(Long goodsId) {
        return goodsRepository.findGoodsById(goodsId).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
    }

    private Category getCategory(GoodsDto goodsDto) {
        return categoryRepository.findById(goodsDto.getCategory()).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
    }

    private void checkTime(Goods goods) {
        LocalDateTime now = LocalDateTime.now();

        if (!goods.getGoodsLimitTime().isAfter(now)) {
            goods.updateIsEnd(true);
        }
    }
}
