package kr.co.board.controller;

import jakarta.validation.Valid;
import kr.co.board.model.dtos.request.PostReqDto;
import kr.co.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody @Valid PostReqDto dto) {
        postService.createPost(dto);
    }

    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable(name = "post_id") Long postId) {
        postService.deletePost(postId);
    }
}
