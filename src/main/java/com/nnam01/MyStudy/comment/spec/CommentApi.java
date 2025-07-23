package com.nnam01.MyStudy.comment.spec;

import com.nnam01.MyStudy.comment.dto.CommentDto;
import com.nnam01.MyStudy.comment.dto.CommentListDto;
import com.nnam01.MyStudy.comment.dto.CommentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Comment API", description = "댓글 관련 API")
@RequestMapping("/api/comments")
@Validated
public interface CommentApi {

  @Operation(summary = "댓글 생성", description = "새로운 댓글을 생성합니다.")
  @Parameter(name = "postId", description = "댓글이 달릴 게시글의 ID", required = true)
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
  @PostMapping("/post/{postId}")
  ResponseEntity<Void> createComment(
      @RequestParam Long postId,
      @RequestBody(required = true) CommentRequestDto request);

  @Operation(summary = "댓글 단건 조회", description = "댓글 id를 통해 단건 조회 생성합니다.")
  @Parameter(name = "commentId", description = "조회할 댓글의 ID", required = true)
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = CommentDto.class))
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
  @GetMapping("/{commentId}")
  ResponseEntity<CommentDto> getCommentById(@PathVariable Long commentId);

  @Operation(summary = "댓글 리스트 조회", description = "게시글 ID를 통해 댓글 리스트를 조회합니다.")
  @Parameter(name = "postId", description = "댓글을 조회할 게시글의 ID", required = true)
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = CommentListDto.class))
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
  @GetMapping("/post/{postId}")
  ResponseEntity<CommentListDto> getCommentListByPostId(
      @PathVariable Long postId);

  @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
  @Parameter(name = "commentId", description = "수정할 댓글의 ID", required = true)
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
  @PatchMapping("/{commentId}")
  ResponseEntity<Void> updateComment(
      @PathVariable Long commentId,
      @RequestBody(required = true) CommentRequestDto request);

  @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
  @Parameter(name = "commentId", description = "삭제할 댓글의 ID", required = true)
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
  @DeleteMapping("/{commentId}")
  ResponseEntity<Void> deleteComment(
      @PathVariable Long commentId,
      @RequestBody(required = true) CommentRequestDto request);
}
