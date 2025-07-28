package com.nnam01.MyStudy.auth.spec;

import com.nnam01.MyStudy.auth.dto.AuthRequestDto;
import com.nnam01.MyStudy.auth.dto.AuthResponseDto;
import com.nnam01.MyStudy.auth.dto.RefreshRequestDto;
import com.nnam01.MyStudy.auth.dto.RefreshResponseDto;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Auth API", description = "인증 관련 API")
@RequestMapping("/api/auth")
@Validated
public interface AuthApi {

  @Operation(summary = "로그인", description = "이메일과 패스워드를 통해 로그인합니다. 성공 시 jwt 발급")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AuthResponseDto.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            })
      })
  @PostMapping("/login")
  ResponseEntity<AuthResponseDto> login(
      @RequestBody(required = true) AuthRequestDto authRequestDto);

  // refresh token
  @Operation(summary = "리프레시", description = "리프레시 토큰을 통해 새로운 액세스 토큰을 발급합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = RefreshResponseDto.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            })
      })
  @PostMapping("/refresh")
  ResponseEntity<RefreshResponseDto> refresh(
      @RequestBody(required = true) RefreshRequestDto requestDto);

  // 로그아웃
  @Operation(summary = "로그아웃", description = "로그아웃을 수행합니다. TEMP: 현재는 리프레시 토큰을 삭제하는 방식")
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
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ProblemDetail.class))
            })
      })
  @PostMapping("/logout")
  ResponseEntity<Void> logout(@RequestAttribute("userId") Long userId);
}
