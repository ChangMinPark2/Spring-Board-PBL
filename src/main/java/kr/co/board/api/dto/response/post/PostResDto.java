package kr.co.board.api.dto.response.post;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PostResDto {
	private String title;
	private String content;
	private List<String> commentContents;
}
