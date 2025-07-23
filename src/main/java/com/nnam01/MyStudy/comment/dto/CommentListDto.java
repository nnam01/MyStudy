package com.nnam01.MyStudy.comment.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentListDto {

  List<CommentDto> comments;
}
