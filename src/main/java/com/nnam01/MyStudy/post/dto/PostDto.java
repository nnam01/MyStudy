package com.nnam01.MyStudy.post.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

  private Long id;
  private String title;
  private String content;
  private Long authorId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Boolean deleted;

}
