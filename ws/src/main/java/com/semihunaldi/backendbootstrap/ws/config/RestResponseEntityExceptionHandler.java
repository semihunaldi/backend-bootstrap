package com.semihunaldi.backendbootstrap.ws.config;

import com.google.common.collect.Lists;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.BaseException;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UnknownException;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserExistsException;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserNotFoundException;
import com.semihunaldi.backendbootstrap.ws.model.BaseResult;
import com.semihunaldi.backendbootstrap.ws.model.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Created by semihunaldi on 27.11.2018
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Environment env;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException(final UserNotFoundException ex, final WebRequest request) {
		BaseResult baseResult = prepareBaseResult(ex, request);
		return handleExceptionInternal(ex, baseResult, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({UserExistsException.class})
	public ResponseEntity<Object> handleUserExistsException(final UserExistsException ex, final WebRequest request) {
		BaseResult baseResult = prepareBaseResult(ex, request);
		return handleExceptionInternal(ex, baseResult, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({UnknownException.class})
	public ResponseEntity<Object> handleUserExistsException(final UnknownException ex, final WebRequest request) {
		BaseResult baseResult = prepareBaseResult(ex, request);
		return handleExceptionInternal(ex, baseResult, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {
		BaseResult baseResult = prepareBaseResult(-1, prepareConstraintViolationMessages(ex), ex, request);
		return handleExceptionInternal(ex, baseResult, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
		BaseResult baseResult = prepareBaseResult(-100, ex, request);
		return handleExceptionInternal(ex, baseResult, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private BaseResult prepareBaseResult(int errorCode, Exception ex, WebRequest request) {
		return new BaseResult(errorCode, ex.getMessage(), prepareErrorMessage(ex, request));
	}

	private BaseResult prepareBaseResult(int errorCode, String message, Exception ex, WebRequest request) {
		return new BaseResult(errorCode, message, prepareErrorMessage(ex, request));
	}

	private BaseResult prepareBaseResult(BaseException ex, WebRequest request) {
		return new BaseResult(ex.errorCode(), ex.description(), prepareErrorMessage(ex, request));
	}

	private Pair<String, String> prepareDescriptions(Exception ex) {
		if(Lists.newArrayList(env.getActiveProfiles()).contains("dev")){
			return Pair.of(ex.getMessage(), ex.getLocalizedMessage());
		}
		if(ex.getCause() != null){
			Pair<String, String> pair = Pair.of(ex.getCause().getClass().getCanonicalName(), ex.getCause().getClass().getCanonicalName());
			Throwable subCause = ex.getCause().getCause();
			if(subCause != null){
				return Pair.of(pair.getFirst().concat(",").concat(subCause.getClass().getCanonicalName()), pair.getSecond()
						.concat(",")
						.concat(subCause.getClass().getCanonicalName()));
			}
			return pair;
		}
		return Pair.of("", "");
	}

	private ErrorMessage prepareErrorMessage(Exception ex, WebRequest request) {
		ErrorMessage.ErrorMessageBuilder errorMessageBuilder = ErrorMessage.builder()
				.message(ex.getMessage())
				.localizedMessage(ex.getLocalizedMessage())
				.contextPath(request.getContextPath())
				.exceptionClass(ex.getClass().getCanonicalName())
				.uri(request.getDescription(false));
		if(ex instanceof BaseException && StringUtils.isBlank(ex.getMessage()) && StringUtils.isBlank(ex.getLocalizedMessage())){
			BaseException baseException = (BaseException) ex;
			errorMessageBuilder.message(baseException.description());
			errorMessageBuilder.localizedMessage(baseException.description());
		}
		if(ex.getCause() != null && !(ex.getCause() instanceof BaseException)){
			Pair<String, String> pair = prepareDescriptions(ex);
			errorMessageBuilder.message(pair.getFirst());
			errorMessageBuilder.localizedMessage(pair.getSecond());
		}
		if(ex instanceof ConstraintViolationException){
			String s = prepareConstraintViolationMessages((ConstraintViolationException) ex);
			errorMessageBuilder.message(s);
			errorMessageBuilder.localizedMessage(s);
		}
		ErrorMessage errorMessage = errorMessageBuilder.build();
		logger.error(errorMessage.getUri() + " &&& " + errorMessage.getMessage(), ex);
		return errorMessage;
	}

	private String prepareConstraintViolationMessages(ConstraintViolationException ex) {
		StringBuilder stringBuilder = new StringBuilder();
		for(ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()){
			constraintViolation.getRootBeanClass().getSimpleName();
			constraintViolation.getPropertyPath();
			stringBuilder
					.append(constraintViolation.getRootBeanClass().getSimpleName())
					.append(".")
					.append(constraintViolation.getPropertyPath().toString())
					.append(" ")
					.append(constraintViolation.getMessage())
					.append("\n");
		}
		return stringBuilder.toString();
	}
}