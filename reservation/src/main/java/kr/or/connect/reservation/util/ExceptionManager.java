package kr.or.connect.reservation.util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kr.or.connect.reservation.util.exception.BaseExceptionType;
import kr.or.connect.reservation.util.exception.ExceptionEnum;
import kr.or.connect.reservation.util.exception.CustomException;

/*
 * XXX: ExceptionHandler 적용해서 모든 Exception을 이곳에서 처리
 * (
 * 		http://localhost:8080/reservation/api/products/1
 * 		http://localhost:8080/reservation/api/products?categoryId=0&start=0
 * )
 * 
 * (https://github.com/123qwe123qwe123qwezxc) 과 같이 404 Not Found도 처리하도록
 * ResponseEntityExceptionHandler를 상속받아 handleNoHandlerFoundException를 Override 하였으나 잘 되지 않아서 나중에 추가 예정
 */
@ControllerAdvice
@ResponseBody
public class ExceptionManager {
	//public class ExceptionManager extends ResponseEntityExceptionHandler {
	private final static Logger logger = Logger.getRootLogger();

	/*@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
		logger.error(exception);
		Error error = Error.create(ExceptionEnum.NOT_FOUND);
		return new ResponseEntity<Object>(error, error.getStatus());
	}*/

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Error> catchedException(CustomException exception) {
		logger.error(exception);
		return new ResponseEntity<Error>(Error.create(exception.getExceptionType()), HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<Error> thrownException(Exception exception) {
		logger.error(exception);
		if (exception.getCause() == null) {
			Error error = Error.create(ExceptionEnum.UNKNOWN_EXCEPTION);
			return new ResponseEntity<Error>(error, error.getStatus());
		} else if (NumberFormatException.class.equals(exception.getCause().getClass())) {
			Error error = Error.create(ExceptionEnum.WRONG_INPUT);
			return new ResponseEntity<Error>(error, error.getStatus());
		} else {
			Error error = Error.create(ExceptionEnum.UNKNOWN_EXCEPTION);
			return new ResponseEntity<Error>(error, error.getStatus());
		}
	}

	static class Error {
		private int code;
		private HttpStatus status;
		private String message;

		public Error() {
			super();
		}

		public Error(int code, HttpStatus status, String message) {
			super();
			this.code = code;
			this.status = status;
			this.message = message;
		}

		static Error create(BaseExceptionType exception) {
			return new Error(exception.getErrorCode(), exception.getHttpStatus(), exception.getErrorMessage());
		}

		public int getCode() {
			return code;
		}

		public HttpStatus getStatus() {
			return status;
		}

		public String getMessage() {
			return message;
		}
	}
}
