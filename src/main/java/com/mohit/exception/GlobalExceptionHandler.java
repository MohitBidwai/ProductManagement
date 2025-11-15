package com.mohit.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mohit.dto.ErrorResponse;

import lombok.Builder;

@ControllerAdvice

public class GlobalExceptionHandler {

//	public ResponseEntity<?>handleNullPointerException(Exception e)
//	{
//		
//		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(e.getMessage()).build();
//		
//		return new ResponseEntity<>
//		
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String ,String> error = new LinkedHashMap<>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		allErrors.stream().forEach(er -> {
			String defaultMessage = er.getDefaultMessage();
			String feild = ((FieldError) er).getField();
			error.put(defaultMessage, feild);
		});
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
