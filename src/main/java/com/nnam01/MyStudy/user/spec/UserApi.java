package com.nnam01.MyStudy.user.spec;

import com.nnam01.MyStudy.user.dto.UserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User API", description = "유저 관련 API")
@RequestMapping("/api/users")
@Validated
public interface UserApi {

  @Operation(summary = "회원가입(유저 생성)", description = "회원가입 합니다.")
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
  ResponseEntity<Void> createUser(
      @RequestBody(required = true)UserRequestDto requestDto);
}
