package kr.or.connect.reservation.exception;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private BaseExceptionType exceptionType;

	public CustomException(BaseExceptionType exceptionType) {
		super(exceptionType.getErrorMessage());
		this.exceptionType = exceptionType;
	}

	public BaseExceptionType getExceptionType() {
		return exceptionType;
	}
}
