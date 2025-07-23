package com.nnam01.MyStudy.dto.comment;

import java.time.LocalDateTime;

public class CommentDto {

  private Long id;
  private Long postId;
  private Long authorId;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Boolean deleted;
}
