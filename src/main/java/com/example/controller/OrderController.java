package com.example.controller;

import com.example.dto.order.DoneDto;
import com.example.dto.order.OrderDto;
import com.example.dto.order.ProgressDto;
import com.example.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{goodsId}/new")
    public ResponseEntity newOrder(@RequestBody OrderDto orderDto, @RequestParam("memberId") String memberIdx,
                                   @PathVariable Long goodsId) {
        orderService.orderGoods(memberIdx, goodsId, orderDto);

        return new ResponseEntity("주문이 성공적으로 되었습니다.", OK);
    }

    @GetMapping("/order/{orderStatusNum}/{orderTypeNum}")
    public ResponseEntity getOrder(@PathVariable Integer orderTypeNum, @RequestParam("memberId") String memberIdx,
                                   @PathVariable Integer orderStatusNum) {
        if (orderStatusNum == 0) {
            List<ProgressDto> progressOrderList = orderService.getProgressOrderList(memberIdx, orderTypeNum, orderStatusNum);
            return new ResponseEntity(progressOrderList, OK);
        } else {
            List<DoneDto> doneOrderList = orderService.getDoneOrderList(memberIdx, orderTypeNum, orderStatusNum);
            return new ResponseEntity<>(doneOrderList, OK);
        }

    }
}
