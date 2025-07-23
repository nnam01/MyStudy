package com.nnam01.MyStudy.post.mapper;

import com.nnam01.MyStudy.post.domain.Post;
import com.nnam01.MyStudy.post.dto.PostDto;
import com.nnam01.MyStudy.post.dto.PostListDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDtoMapper {

  PostDto toDto(Post post);

  List<PostDto> toDtoList(List<Post> posts);

  default PostListDto toPostListDto(List<Post> posts) {
    return new PostListDto(toDtoList(posts));
  }
}
