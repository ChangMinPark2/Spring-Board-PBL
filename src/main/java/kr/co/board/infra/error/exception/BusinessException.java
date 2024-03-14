package kr.co.board.infra.error.exception;

import kr.co.board.infra.error.model.ErrorCode;

public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;

    public BusinessException (ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(String message){
        super(message);
    }
}
