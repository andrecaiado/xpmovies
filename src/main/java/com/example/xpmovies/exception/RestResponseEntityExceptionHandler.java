package com.example.xpmovies.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.UNPROCESSABLE_ENTITY.value(), 
		        "Validation error. Check 'errors' field for details."
				);
		
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.unprocessableEntity().body(errorResponse);
	
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
		        "Required request parameter '" + ex.getParameterName() + "' for method parameter type " + ex.getParameterType() + " is not present."
				);
		
		return ResponseEntity.badRequest().body(errorResponse);
	}

    @ExceptionHandler({ MovieNotFoundException.class })
    public ResponseEntity<Object> handleMovieNotFound(final RuntimeException ex, final WebRequest request) {
        logger.error("404 Status Code", ex);
        ErrorResponse errorResponse = new ErrorResponse(
        		HttpStatus.NOT_FOUND.value(),
        		ex.getMessage()
        		);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
}
