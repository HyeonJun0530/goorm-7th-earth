package com.example.service.board;

import com.example.dto.board.CommentDto;
import com.example.entity.Member;
import com.example.entity.board.Board;
import com.example.entity.board.Comment;
import com.example.exception.global.exception.ResourceNotFoundException;
import com.example.repository.BoardRepository;
import com.example.repository.CommentRepository;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.exception.global.GlobalErrorCode.RESOURCE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void createComment(String memberIdx, Long boardId, CommentDto commentDto) {
        Comment comment;
        Member member = memberService.getMember(memberIdx);
        Board board = boardRepository.findBoardByBoardIdWithGetComment(boardId)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        if (commentDto.getParentCommentId() != null) {
            Comment parent = board.getCommentList().stream().filter(comment1 -> comment1.getId() == commentDto.getParentCommentId())
                    .findFirst().orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
            comment = Comment.createComment(member, board, commentDto.getContent(), parent);
        } else {
            comment = Comment.createComment(member, board, commentDto.getContent());
        }

        commentRepository.save(comment);
    }


}
