package kr.co.board.global.error.handler;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.board.global.error.exception.CommentAlreadyDeletedException;
import kr.co.board.global.error.exception.NotFoundException;
import kr.co.board.global.error.model.ResponseFormat;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	protected ResponseFormat handleNotFoundException(NotFoundException e) {
		return ResponseFormat.fail(e.getMessage());
	}

	@ExceptionHandler(CommentAlreadyDeletedException.class)
	protected ResponseFormat handleDeletedException(CommentAlreadyDeletedException e) {
		return ResponseFormat.fail(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseFormat handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		String errorMessage = fieldErrors.get(0).getDefaultMessage();

		return ResponseFormat.fail(errorMessage);
	}
}
