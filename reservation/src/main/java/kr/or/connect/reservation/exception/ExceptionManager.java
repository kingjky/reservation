package kr.or.connect.reservation.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionManager {
	private final static Logger logger = Logger.getRootLogger();

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
