package kr.co.board.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.board.api.dto.request.post.PostCreateDto;
import kr.co.board.api.dto.request.post.PostUpdateDto;
import kr.co.board.api.service.PostService;
import kr.co.board.global.error.model.ResponseFormat;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@PostMapping
	public void create(@RequestBody @Valid PostCreateDto dto) {
		postService.createPost(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		postService.deletePost(id);
	}

	@PutMapping
	public void update(@RequestBody @Valid PostUpdateDto dto) {
		postService.update(dto);
	}

	@GetMapping("/{id}")
	public ResponseFormat read(@PathVariable(name = "id") Long id) {
		return ResponseFormat.ok(postService.readPost(id));
	}

	// 마지막 ID와 페이지 크기를 파라미터로 받는다.
	// @RequestParam을 사용하여 lastId가 없는 경우 기본값으로 0을 사용하고, size의 기본값을 설정할 수 있습니다.
	@GetMapping()
	public ResponseFormat getNextPostTitles(
		@RequestParam(name = "lastId", required = false, defaultValue = "0") Long lastId,
		@RequestParam(name = "size", defaultValue = "10") int size) {

		return ResponseFormat.ok(postService.readAllPostTitles(lastId, size));
	}
}
