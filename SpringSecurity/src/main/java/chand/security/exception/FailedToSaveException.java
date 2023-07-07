package chand.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedToSaveException extends RuntimeException {

	public FailedToSaveException() {
		super();
	}
	
	public FailedToSaveException(String reason) {
		super(reason);
	}
}
