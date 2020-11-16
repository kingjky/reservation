package kr.or.connect.reservation.util.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum implements BaseExceptionType {
	
	UNKNOWN_EXCEPTION(1000, HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 예외가 발생했습니다."),
	WRONG_INPUT(1001, HttpStatus.BAD_REQUEST, "입력의 형식이 잘못되었습니다."),
	NOT_FOUND(1002, HttpStatus.NOT_FOUND, "찾는 페이지가 없습니다.");

	private int errorCode;
	private HttpStatus httpStatus;
	private String errorMessage;

	private ExceptionEnum(int errorCode, HttpStatus httpStatus, String errorMessage) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}
	
    @Override
	public int getErrorCode() {
		return errorCode;
	}
	
    @Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
    @Override
	public String getErrorMessage() {
		return errorMessage;
	}
}
