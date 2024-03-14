package kr.co.board.infra.error.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    //Success
    SUCCESS_OK("요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
    SUCCESS_CREATE("요청이 성공적으로 처리되었으며 새로운 리소스가 생성되었습니다.", HttpStatus.CREATED),
    SUCCESS_ACCEPTED("요청이 성공적으로 처리되었지만, 결과는 아직 완료되지 않았습니다..", HttpStatus.ACCEPTED),
    SUCCESS_NO_VALUE("요청이 성공적으로 처리되었지만, 응답 데이터가 없습니다..", HttpStatus.NO_CONTENT),

    //FailNotFound 404 error
    FAIL_NOT_CONTENT_FOUND("CONTENT를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_NOT_POST_FOUND("POST를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private String message;
    private HttpStatus statusCode;
}
