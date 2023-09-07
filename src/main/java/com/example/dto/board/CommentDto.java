package com.example.dto.board;

import lombok.Data;

@Data
public class CommentDto {

    private String content;
    private Long parentCommentId;

}
