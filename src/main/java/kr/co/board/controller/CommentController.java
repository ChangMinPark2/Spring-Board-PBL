package kr.co.board.controller;

import jakarta.validation.Valid;
import kr.co.board.model.dtos.request.CommentReqDto;
import kr.co.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{post_id}")
    public void createComment(@PathVariable(name = "post_id") Long postId,
                              @RequestBody @Valid CommentReqDto dto) {
        commentService.createComment(postId, dto);
    }
}
