package chand.security.controller.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import chand.security.payload.response.ApiErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletResponse response){
		ApiErrorResponse errorResponse =new ApiErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.setMessage("Somefields are not valid");
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> errorResponse.addError(fieldError.getField(), fieldError.getDefaultMessage()));
		return errorResponse;
	}
	
	@ExceptionHandler(Exception.class)
	public ApiErrorResponse handleGloabalExceptions(Exception ex, HttpServletResponse response,HttpServletRequest request){
		ApiErrorResponse errorResponse =new ApiErrorResponse();
		HttpStatus status = HttpStatus.valueOf(response.getStatus());
		errorResponse.setStatus(status.value());
		errorResponse.setError(status.getReasonPhrase());
		errorResponse.setMessage(ex.getMessage());
		return errorResponse;
	}
}
