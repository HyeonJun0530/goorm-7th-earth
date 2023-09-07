package com.example.repository;

import com.example.entity.order.Order;
import com.example.entity.order.OrderStatus;
import com.example.entity.order.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByGoodsId(Long goodsId);

    List<Order> findOrdersByMember_IdAndOrderTypeAndOrderStatus(Long memberId,
                                                                OrderType orderType, OrderStatus orderStatus);

    List<Order> findOrdersByMember_IdAndOrderType(Long memberId, OrderType orderType);
}