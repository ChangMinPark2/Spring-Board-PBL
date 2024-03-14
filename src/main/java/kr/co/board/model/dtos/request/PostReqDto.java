package kr.co.board.model.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PostReqDto(

        @NotBlank
        String title,
        @NotBlank
        String content
) {
}
