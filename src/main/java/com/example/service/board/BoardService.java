package com.example.service.board;

import com.example.dto.board.BoardDto;
import com.example.entity.board.Board;
import com.example.entity.board.Comment;
import com.example.entity.goods.Goods;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.repository.BoardRepository;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.exception.global.GlobalErrorCode.RESOURCE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void createBoard(String boardContent, Goods goods) {
        Board board = Board.createBoard(boardContent, goods);

        boardRepository.save(board);
    }

    public BoardDto getBoard(Long boardId) {
        Board board = boardRepository.findBoardByBoardIdWithGetComment(boardId)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        return BoardDto.toBoardDto(board);
    }
}
