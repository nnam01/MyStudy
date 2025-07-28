package com.nnam01.MyStudy.post.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostListDto {

  List<PostDto> posts;
}
