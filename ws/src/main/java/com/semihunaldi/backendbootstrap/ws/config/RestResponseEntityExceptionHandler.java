package com.semihunaldi.backendbootstrap.ws.config;

import com.semihunaldi.backendbootstrap.ws.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by semihunaldi on 27.11.2018
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	//TODO: new detailed handlers

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setLocalizedMessage(ex.getLocalizedMessage());
		errorMessage.setContextPath(request.getContextPath());
		errorMessage.setExceptionClass(ex.getClass().getCanonicalName());
		errorMessage.setUri(request.getDescription(false));
		if(request.getUserPrincipal() != null){
			request.getRemoteUser();
		}
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}