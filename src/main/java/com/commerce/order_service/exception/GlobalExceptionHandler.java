package com.commerce.order_service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.commerce.order_service.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<?> handlesInsufficientStockException(InsufficientStockException ex){
		
		ErrorResponse error = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.CONFLICT.value())
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<?> handlesOrderNotFoundException(OrderNotFoundException ex){
		
		
		ErrorResponse error = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handlesAccessDeniedException(AccessDeniedException ex){
		
		
		ErrorResponse error = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.FORBIDDEN.value())
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(ProductLookupException.class)
	public ResponseEntity<?> handlesFeignClientError(ProductLookupException ex){
		
		
		ErrorResponse error = new ErrorResponse(
				
				ex.getMessage(),
				HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(
	        MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(fieldError ->
	            errors.put(fieldError.getField(), fieldError.getDefaultMessage())
	    );
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlesGenericException(Exception ex){
		
		
		ErrorResponse error = new ErrorResponse(
				
				"Unexpected Error Occured",
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		
	}
	
}
