package com.example.entity.selling;

import com.example.entity.BaseTimeEntity;
import com.example.entity.Member;
import com.example.entity.goods.Goods;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Selling extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selling_id")
    private Long id;

    @Column(name = "selling_count")
    private Integer sellingCount;

    @Column(name = "selling_price")
    private Integer sellingPrice;

    @Column(name = "selling_status")
    @Enumerated(EnumType.STRING)
    private SellingStatus sellingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;
}
