package kr.co.board.global.error.exception;

import kr.co.board.global.error.model.ErrorCode;

public class CommentAlreadyDeletedException extends BusinessException {
	public CommentAlreadyDeletedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
