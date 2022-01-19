package com.waracle.cakemgr.validator.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CakeValidationErrorHandler extends ResponseEntityExceptionHandler
{
	/**
	 * Controller advice.  
	 * 
	 * @param validationException binding errors
	 * @param headers request headers
	 * @param status status code
	 * @param request web interceptors
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(
			final BindException validationException,
			final HttpHeaders headers,
			final HttpStatus status,
			final WebRequest request)
	{
		List<CakeFieldValidationMessage> validationErrors = 
				validationException
				.getBindingResult()
				.getFieldErrors().stream()
				.map(fieldError -> new CakeFieldValidationMessage(fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}
}
