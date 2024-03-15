package kr.co.board.global.error.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
	//Success
	SUCCESS_CREATE("요청이 성공적으로 처리되었으며 새로운 리소스가 생성되었습니다.", HttpStatus.CREATED),
	SUCCESS_ACCEPTED("요청이 성공적으로 처리되었지만, 결과는 아직 완료되지 않았습니다..", HttpStatus.ACCEPTED),
	SUCCESS_NO_VALUE("요청이 성공적으로 처리되었지만, 응답 데이터가 없습니다..", HttpStatus.NO_CONTENT),
	FAIL_NULL("실행에 실패했습니다.", HttpStatus.BAD_REQUEST),
	//FailNotFound 404 error
	FAIL_NOT_COMMENT_FOUND("COMMENT를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	FAIL_ALREADY_DELETE_COMMENT("COMMENT가 삭제되었습니다.", HttpStatus.CONFLICT),
	FAIL_ALREADY_DELETE_POST("POST가 삭제되었습니다.", HttpStatus.CONFLICT),
	FAIL_NOT_POST_FOUND("POST를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

	private String message;
	private HttpStatus statusCode;
}
