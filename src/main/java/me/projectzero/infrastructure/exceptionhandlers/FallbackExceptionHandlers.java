package me.projectzero.infrastructure.exceptionhandlers;

import me.projectzero.infrastructure.enumeration.ResponseCode;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseError;
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

@ControllerAdvice
@Order
public class FallbackExceptionHandlers {

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.PROPERTY_REFERENCE).build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.MISSING_SERVLET_REQUEST_PARAMETER, exception.getParameterName(), exception.getParameterType()).build());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatchException(final TypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.TYPE_MISMATCH, exception.getValue(), exception.getPropertyName(), exception.getRequiredType()).build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ServiceResponseError.builder().throwable(exception).message(exception.getSupportedHttpMethodsMessage()).code(ResponseCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED).build());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> handleMissingServletRequestPartException(final MissingServletRequestPartException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.MISSING_SERVLET_REQUEST_PART, exception.getRequestPartName()).build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.METHOD_ARGUMENT_TYPE_MISMATCH, exception.getName(), Objects.requireNonNull(exception.getRequiredType()).getName()).build());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.NO_HANDLER_FOUND, exception.getHttpMethod(), exception.getRequestURL()).build());
    }

    @ExceptionHandler(NestedRuntimeException.class)
    public ResponseEntity<Object> handleNestedRuntimeException(NestedRuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).code(ResponseCode.NESTED_RUNTIME).message(exception.getMessage()).build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceResponseError.builder().throwable(exception).code(ResponseCode.ERROR).message(exception.getMessage()).build());
    }

}
