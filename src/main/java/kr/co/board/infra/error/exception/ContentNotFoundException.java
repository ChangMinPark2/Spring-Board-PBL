package kr.co.board.infra.error.exception;

import kr.co.board.infra.error.model.ErrorCode;

public class ContentNotFoundException extends BusinessException{
    public ContentNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ContentNotFoundException(String message) {
        super(message);
    }
}
