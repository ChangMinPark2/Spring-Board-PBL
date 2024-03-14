package kr.co.board.infra.error.exception;

import kr.co.board.infra.error.model.ErrorCode;

public class PostNotFoundException extends BusinessException{
    public PostNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
