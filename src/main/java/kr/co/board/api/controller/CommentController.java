package kr.co.board.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.board.api.dto.request.comment.CommentCreateDto;
import kr.co.board.api.dto.request.comment.CommentUpdateDto;
import kr.co.board.api.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping
	public void create(@RequestBody @Valid CommentCreateDto dto) {
		commentService.create(dto);
	}

	@PutMapping()
	public void update(@RequestBody @Valid CommentUpdateDto dto) {
		commentService.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		commentService.delete(id);
	}
}
