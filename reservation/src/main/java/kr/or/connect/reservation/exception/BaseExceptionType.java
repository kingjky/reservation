package kr.or.connect.reservation.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
	int getErrorCode();

	HttpStatus getHttpStatus();

	String getErrorMessage();
}
