package me.projectzero.api.exceptionhandlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import me.projectzero.domain.enumeration.ErrorCode;
import me.projectzero.service.error.ServiceErrorResponse;
import me.projectzero.service.error.sub.ValidationError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Order(0)
@ControllerAdvice
public class ValidationExceptionHandlers {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException exception) {
        List<ValidationError> validationErrors = new ArrayList<>(exception.getErrorCount());
        for (FieldError fieldError : exception.getFieldErrors()) {
            validationErrors.add(
                    ValidationError.builder()
                            .field(fieldError.getField())
                            .rejectedValue(fieldError.getRejectedValue())
                            .code(fieldError.getCode())
                            .message(fieldError.getDefaultMessage())
                            .build()
            );
        }
        for (ObjectError globalError : exception.getGlobalErrors()) {
            validationErrors.add(
                    ValidationError.builder()
                            .code(globalError.getCode())
                            .message(globalError.getDefaultMessage())
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.VALIDATION).validation(validationErrors).wrap());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<ValidationError> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<?> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            errors.add(
                    ValidationError.builder()
                            .field(field)
                            .rejectedValue(violation.getInvalidValue())
                            .code(violation.getMessageTemplate())
                            .message(violation.getMessage())
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(ErrorCode.VALIDATION).validation(errors).wrap());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(exception.getMessage()).wrap());
    }

}
