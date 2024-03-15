package kr.co.board.api.service;

import static kr.co.board.api.persistence.entity.Post.*;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.api.dto.request.post.PostCreateDto;
import kr.co.board.api.dto.request.post.PostUpdateDto;
import kr.co.board.api.dto.response.post.PostResDto;
import kr.co.board.api.persistence.entity.Comment;
import kr.co.board.api.persistence.entity.Post;
import kr.co.board.api.persistence.repository.CommentRepository;
import kr.co.board.api.persistence.repository.PostRepository;
import kr.co.board.global.error.exception.NotFoundException;
import kr.co.board.global.error.model.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public void createPost(PostCreateDto dto) {
		final Post post = create(dto);

		postRepository.save(post);
	}

	public void deletePost(Long id) {
		final Post post = findById(id);

		final List<Comment> comments = commentRepository.findByPost(post);

		commentRepository.deleteAll(comments);
		postRepository.delete(post);
	}

	public void update(PostUpdateDto dto) {
		final Post post = findById(dto.id());
		validateDeleted(post);

		post.update(dto);
	}

	@Transactional(readOnly = true)
	public PostResDto readPost(Long id) {
		final Post post = findById(id);
		validateDeleted(post);

		final List<String> comments = commentRepository.findByPost(post)
			.stream()
			.map(Comment::getContent)
			.toList();

		return read(post, comments);
	}

	@Transactional(readOnly = true)
	public List<String> readAllPostTitles(Long lastId, int size) {
		Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.ASC, "createAt"));
		return postRepository.findPostTitlesAfterLastId(lastId, pageable);
	}

	public Post findById(Long id) {
		return postRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_NOT_POST_FOUND));
	}

	private void validateDeleted(Post post) {
		if (Boolean.TRUE.equals(post.getDeleted())) {
			throw new NotFoundException(ErrorCode.FAIL_ALREADY_DELETE_COMMENT);
		}
	}
}
