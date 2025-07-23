package com.nnam01.MyStudy.comment.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentDto {

  private Long id;
  private Long postId;
  private Long authorId;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Boolean deleted;
}
