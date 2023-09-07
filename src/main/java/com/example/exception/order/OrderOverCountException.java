package com.example.exception.order;

import com.example.exception.global.ExceptionCode;
import lombok.Getter;

import static com.example.exception.order.OrderException.*;

@Getter
public class OrderOverCountException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public OrderOverCountException() {
        super(ORDER_OVER_COUNT.getMessage());
        this.exceptionCode = ORDER_OVER_COUNT;
    }

}
