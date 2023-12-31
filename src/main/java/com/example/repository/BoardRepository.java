package com.example.repository;

import com.example.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.id =:boardId")
    Optional<Board> findBoardByBoardIdWithGetComment(@Param("boardId") Long boardId);

    Board findBoardByGoodsId(Long goodsId);
}