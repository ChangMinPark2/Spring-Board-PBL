package kr.co.board.service;

import kr.co.board.infra.error.exception.PostNotFoundException;
import kr.co.board.infra.error.model.ErrorCode;
import kr.co.board.model.dtos.request.CommentReqDto;
import kr.co.board.model.mapper.CommentMapper;
import kr.co.board.persistence.entity.Comment;
import kr.co.board.persistence.entity.Post;
import kr.co.board.persistence.repository.CommentRepository;
import kr.co.board.persistence.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.board.model.mapper.CommentMapper.create;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void createComment(Long postId, CommentReqDto dto) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.FAIL_NOT_POST_FOUND));

        final Comment comment = create(post, dto);

        commentRepository.save(comment);
    }
}
