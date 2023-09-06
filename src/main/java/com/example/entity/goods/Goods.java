package com.example.entity.goods;

import com.example.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class Goods extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;

    @Column(name = "goods_name")
    private String name;

    @Column(name = "goods_introduction")
    private String introduction;

    @Column(name = "goods_link")
    private String link;

    @Column(name = "goods_price")
    private Integer goodsPrice;

    @Column(name = "delivery_fee")
    private Integer deliveryFee;

    @Embedded
    private Address address;

    @Column(name = "goods_is_deleted")
    private boolean isDeleted;

    @Column(name = "goods_limit_count")
    private Integer goodsLimitCount;

    private LocalDateTime goodsLimitTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
