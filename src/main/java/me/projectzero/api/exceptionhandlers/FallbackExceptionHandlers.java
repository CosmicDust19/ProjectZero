package me.projectzero.api.exceptionhandlers;

import me.projectzero.domain.enumeration.ErrorCode;
import me.projectzero.infrastructure.common.util.ExceptionMessageUtils;
import me.projectzero.service.error.ServiceErrorResponse;
import me.projectzero.service.error.ServiceErrorResponseDirector;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

@Order
@ControllerAdvice
public class FallbackExceptionHandlers {

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.PROPERTY_REFERENCE).wrap());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.MISSING_SERVLET_REQUEST_PARAMETER, exception.getParameterName(), exception.getParameterType()).wrap());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatchException(final TypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.TYPE_MISMATCH, exception.getValue(), exception.getPropertyName(), exception.getRequiredType()).wrap());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ServiceErrorResponse.thrown(exception).message(ExceptionMessageUtils.buildSupportedHttpMethodsMessage(exception)).code(ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED).wrap());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> handleMissingServletRequestPartException(final MissingServletRequestPartException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.MISSING_SERVLET_REQUEST_PART, exception.getRequestPartName()).wrap());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH, exception.getName(), Objects.requireNonNull(exception.getRequiredType()).getName()).wrap());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.NO_HANDLER_FOUND, exception.getHttpMethod(), exception.getRequestURL()).wrap());
    }

    @ExceptionHandler(NestedRuntimeException.class)
    public ResponseEntity<Object> handleNestedRuntimeException(NestedRuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).code(ErrorCode.NESTED_RUNTIME).message(exception.getMessage()).wrap());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceErrorResponseDirector.unexpected().message(throwable.getMessage()).wrap());
    }

}
