package kr.co.board.service;

import kr.co.board.infra.error.exception.PostNotFoundException;
import kr.co.board.infra.error.model.ErrorCode;
import kr.co.board.model.dtos.request.PostReqDto;
import kr.co.board.persistence.entity.Comment;
import kr.co.board.persistence.entity.Post;
import kr.co.board.persistence.repository.CommentRepository;
import kr.co.board.persistence.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static kr.co.board.model.mapper.PostMapper.create;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void createPost(PostReqDto dto) {
        final Post post = create(dto);

        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(ErrorCode.FAIL_NOT_POST_FOUND));

        final List<Comment> comments = commentRepository.findByPost(post);

        commentRepository.deleteAll(comments);
        postRepository.delete(post);
    }
}
