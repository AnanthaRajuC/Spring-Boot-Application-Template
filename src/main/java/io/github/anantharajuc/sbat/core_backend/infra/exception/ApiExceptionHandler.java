package io.github.anantharajuc.sbat.core_backend.infra.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

/**
 * Maps exceptions thrown by REST controllers to RFC 9457 problem details. Thymeleaf pages keep using the
 * regular error page.
 */
@Log4j2
@RestControllerAdvice(annotations=RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(AuthenticationException.class)
	public ProblemDetail handleAuthentication(AuthenticationException exception)
	{
		return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ProblemDetail handleAccessDenied(AccessDeniedException exception)
	{
		return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Access Denied");
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ProblemDetail handleNotFound(ResourceNotFoundException exception)
	{
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException exception)
	{
		log.info("Data integrity violation: {}", exception.getMostSpecificCause().getMessage());

		return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "The request conflicts with existing data.");
	}

	@ExceptionHandler(OtherExceptions.class)
	public ProblemDetail handleOther(OtherExceptions exception)
	{
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
}
