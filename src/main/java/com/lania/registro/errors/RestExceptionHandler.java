package com.lania.registro.errors;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.core.Ordered;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	   @ExceptionHandler(EntityNotFoundException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
	       ApiError apiError = new ApiError(NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	   
	   @ExceptionHandler(DuplicateEntityException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(DuplicateEntityException ex) {
	       ApiError apiError = new ApiError(UNPROCESSABLE_ENTITY);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	   
	   @ExceptionHandler(InvalidEntityException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(InvalidEntityException ex) {
	       ApiError apiError = new ApiError(BAD_REQUEST);
	       apiError.setMessage(ex.getMessage());
	       apiError.setSubErrors(ex.getErrores());
	       return buildResponseEntity(apiError);
	   }
	   
	    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	        return new ResponseEntity<>(apiError, apiError.getStatus());
	    }
}
