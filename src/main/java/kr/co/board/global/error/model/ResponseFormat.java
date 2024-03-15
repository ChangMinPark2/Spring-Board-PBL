package kr.co.board.global.error.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseFormat<T> {
	private boolean result;

	//제네릭 : 컴파일을 할 때 미리 데이터 타입을 미리 지정하는 것.
	//data : 성공 시, 전달할 데이터
	private T data;

	//성공 혹은 실패 에 따른 설명 메시지
	private String message;

	//응답 코드
	//2XX : 성공
	//4XX : 실패
	private HttpStatus status;

	public static <T> ResponseFormat ok(T data) {
		return ResponseFormat.builder()
			.result(true)
			.data(data)
			.message(ErrorCode.SUCCESS_CREATE.getMessage())
			.status(ErrorCode.SUCCESS_CREATE.getStatusCode())
			.build();
	}

	public static ResponseFormat fail(String message) {
		return ResponseFormat.builder()
			.result(false)
			.data(null)
			.message(message)
			.status(ErrorCode.FAIL_NULL.getStatusCode())
			.build();
	}
}
