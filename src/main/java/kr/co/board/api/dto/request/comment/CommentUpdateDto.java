package kr.co.board.api.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentUpdateDto(
	@NotNull
	Long id,
	@NotBlank
	@Size(max = 100, message = "100자를 넘길 수 없습니다.")
	String content
) {
}