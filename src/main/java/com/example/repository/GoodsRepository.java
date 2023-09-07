package com.example.repository;

import com.example.entity.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @Query("select g from Goods g join fetch g.orderList where g.id =: goodsId")
    Optional<Goods> findGoodsById(@Param("goodsId") Long goodsId);
}