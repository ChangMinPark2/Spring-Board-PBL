package kr.co.board.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.api.dto.request.comment.CommentCreateDto;
import kr.co.board.api.dto.request.comment.CommentUpdateDto;
import kr.co.board.api.persistence.entity.Comment;
import kr.co.board.api.persistence.entity.Post;
import kr.co.board.api.persistence.repository.CommentRepository;
import kr.co.board.global.error.exception.NotFoundException;
import kr.co.board.global.error.model.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
	private final PostService postService;
	private final CommentRepository commentRepository;

	/**
	 * method name : 더 명확하면서도 간결했으면 좋겠음. 문법적으로도
	 * post에 데이터에 접근할 때, DB Table에 직접 접근하는 것보단, 객체에 접근하도록 하는게 좋을 것 같음
	 *
	 * @param dto : comment id가 아니고 post id인데, 굳이 PathVariable 받는 이유가 궁금함, -> 그리고 또 DTO가 필드 하나만 가진게 좀 어색했음
	 * dto 분리 -> 코드를 수정해야할 때, 단 하나의 이유만으로 변경되어야 한다
	 */
	public void create(CommentCreateDto dto) {
		final Post post = postService.findById(dto.id());
		final Comment comment = Comment.create(dto.content(), post);

		commentRepository.save(comment);
	}

	/**
	 * method name
	 * - 또 너무 명확하면 안됨. -> 범용성이 떨어짐,
	 * - 또 너무 명확하면, 메서드명만으로 해당 메서드가 어떤 행위를 할 지 유추됨 -> 캡슐화(정보 은닉) 꺠짐
	 * - 클린 코드에서 getter 사용을 자제하는 이유 -> 캡슐화 깨짐 -> 위 이유와 비슷한 이유라 생각함.
	 * - JPA 변경감지
	 *  - 영속성 컨텍스트
	 *      1차 캐시 -> 첫 조회된 데이터의 스냅샷을 저장해.
	 *  - 변경 후 코멘트 데이터와 1차 캐시에 들어 있는 데이터를 비교해 -> 변경 감지
	 *  - 쓰기 지연 저장소 -> update 쿼리가 저장됨
	 *  - 커밋 시, 뭐가 일어남 -> 플러시 -> 플러시될 때, 쓰기 지연 저장소에 있는 쿼리를 다 날림.
	 * @param dto
	 */
	public void update(CommentUpdateDto dto) {
		final Comment comment = commentRepository.findById(dto.id())
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_NOT_COMMENT_FOUND));

		validateDeleted(comment);
		comment.update(dto.content());
	}

	public void delete(Long id) {
		final Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(ErrorCode.FAIL_NOT_COMMENT_FOUND));

		commentRepository.delete(comment);
	}

	/**
	 * 예외 클래스명 같은 경우, 어떤 예외인지가 앞에 오는걸 좋아함 -> 그리고 더 간결한게 최고임 -> 범용성이 있어야 함
	 * ~~~로 인하여 예외처리가 된
	 * test
	 */
	private void validateDeleted(Comment comment) {
		if (Boolean.TRUE.equals(comment.getDeleted())) {
			throw new NotFoundException(ErrorCode.FAIL_ALREADY_DELETE_COMMENT);
		}
	}
}
