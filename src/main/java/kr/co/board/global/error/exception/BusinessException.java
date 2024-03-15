package kr.co.board.global.error.exception;

import kr.co.board.global.error.model.ErrorCode;

public class BusinessException extends RuntimeException {
	private ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
