package com.coveo.citiesfinder.aspect;

import com.coveo.citiesfinder.models.response.ApiError;
import org.geonames.GeoNamesException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * A global exception handler handles any kind of exception arises in program.
 * Specific exception handler invoke before more general exception handler.
 * If finally the exception cannot be handled, an internal server exception with status 500 is returned.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> exceptionHandler(ConstraintViolationException exception) {
        ApiError errorResponse = new ApiError(BAD_REQUEST, exception.getMessage(), exception);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(GeoNamesException.class)
    protected ResponseEntity<Object> exceptionHandler(GeoNamesException exception) {
        ApiError errorResponse = new ApiError(INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(),
                ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    //If exception can't be handled.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnknownException(Exception e) {

        ApiError errorResponse = new ApiError(INTERNAL_SERVER_ERROR);
        errorResponse.setMessage("We are working on it, It will be fixed ASAP");
        errorResponse.setDebugMessage(e.getMessage());
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
