package com.example.controller;

import com.example.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/{boardId}")
//    public ResponseEntity getBoard(@PathVariable Long boardId) {
//
//    }
}
