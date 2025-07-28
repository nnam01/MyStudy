package com.nnam01.MyStudy.comment.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.nnam01.MyStudy.comment.domain.Comment;
import com.nnam01.MyStudy.comment.dto.CommentDto;
import com.nnam01.MyStudy.comment.dto.CommentListDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CommentDtoMapper {
  CommentDto toDto(Comment comment);

  List<CommentDto> toDtoList(List<Comment> comments); // 기본 매핑

  default CommentListDto toCommentListDto(List<Comment> comments) {
    return new CommentListDto(toDtoList(comments));
  }
}
