package kr.co.board.global.error.exception;

import kr.co.board.global.error.model.ErrorCode;

public class NotFoundException extends BusinessException {
	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
