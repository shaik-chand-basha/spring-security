package chand.security.controller.advice;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import chand.security.exception.UserNotFoundException;

@RestControllerAdvice
public class UserInfoControllerAdvice {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
		String message = ex.getMessage();
		return ResponseEntity.of(Optional.ofNullable(message));
	}
}
