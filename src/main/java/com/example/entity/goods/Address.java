package com.example.entity.goods;

import com.example.entity.BaseTimeEntity;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address extends BaseTimeEntity {

    private Integer mapX;

    private Integer mapY;

}
