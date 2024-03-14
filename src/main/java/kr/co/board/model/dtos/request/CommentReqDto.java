package kr.co.board.model.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CommentReqDto(
        @NotBlank
        String content
) {
}
