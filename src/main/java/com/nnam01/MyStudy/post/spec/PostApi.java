package com.nnam01.MyStudy.post.spec;

import com.nnam01.MyStudy.post.dto.PostDto;
import com.nnam01.MyStudy.post.dto.PostListDto;
import com.nnam01.MyStudy.post.dto.PostRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Post API", description = "게시글 관련 API")
@RequestMapping("/api/posts")
@Validated
public interface PostApi {

  @Operation(summary = "게시글 등록", description = "게시글을 저장합니다.")

  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "201",
              description = "Created",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = Void.class))
              }),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          )
      })
  @PostMapping("/")
  ResponseEntity<Void> createPost(
      @RequestBody(required = true) PostRequestDto postRequestDto);

  @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
  @Parameter(name = "postId", description = "수정할 게시글의 ID", required = true)
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "204",
              description = "No Content",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = Void.class))
              }),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          )
      })
  @PatchMapping("/{postId}")
  ResponseEntity<Void> updatePost(
      @PathVariable Long postId, @RequestBody(required = true) PostRequestDto postRequestDto);

  @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
  @Parameter(name = "postId", description = "삭제할 게시글의 ID", required = true)
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "204",
              description = "No Content"),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          )
      })
  @DeleteMapping("/{postId}")
  ResponseEntity<Void> deletePost(@PathVariable Long postId);

  @Operation(summary = "게시글 조회", description = "게시글을 조회합니다.")
  @Parameter(name = "postId", description = "조회할 게시글의 ID", required = true)
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = PostDto.class))
              }),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          )
      })
  @GetMapping("/{postId}")
  ResponseEntity<PostDto> getPost(@PathVariable Long postId);

  @Operation(summary = "게시글 목록 조회(아직 미구현)", description = "게시글 목록을 조회합니다.")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = PostListDto.class))
              }),
          @ApiResponse(
              responseCode = "400",
              description = "Bad Request",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal Server Error",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProblemDetail.class))
              }
          )
      })
  @GetMapping("/")
  ResponseEntity<PostListDto> getPostList();
}
