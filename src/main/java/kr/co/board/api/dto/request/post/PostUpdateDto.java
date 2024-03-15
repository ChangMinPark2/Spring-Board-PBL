package kr.co.board.api.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostUpdateDto(

	@NotNull
	Long id,
	@NotBlank
	@Size(max = 20, message = "20자를 넘길 수 없습니다.")
	String title,
	@NotBlank
	@Size(max = 100, message = "100자를 넘길 수 없습니다.")
	String content
) {
}

