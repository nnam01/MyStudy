package com.nnam01.MyStudy.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long authorId;
    private String content;
}

