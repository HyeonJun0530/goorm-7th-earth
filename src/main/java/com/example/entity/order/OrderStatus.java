package com.example.entity.order;

import com.example.exception.global.GlobalErrorCode;
import com.example.exception.global.exception.ResourceNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrderStatus {
    PROCESSING(0), END(1);

    private final Integer status;

    OrderStatus(Integer status) {
        this.status = status;
    }

    public static OrderStatus getOrderStatus(Integer status) {
        return Arrays.stream(OrderStatus.values()).filter(orderStatus -> orderStatus.getStatus() == status)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(GlobalErrorCode.RESOURCE_NOT_FOUND));
    }
}
