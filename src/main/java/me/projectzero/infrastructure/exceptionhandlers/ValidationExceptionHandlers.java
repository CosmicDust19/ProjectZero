package me.projectzero.infrastructure.exceptionhandlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import me.projectzero.infrastructure.enumeration.ResponseCode;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseError;
import me.projectzero.infrastructure.serviceresponse.ValidationError;
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

@RequiredArgsConstructor
@ControllerAdvice
@Order(0)
public class ValidationExceptionHandlers {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException exception) {
        List<ValidationError> validationErrors = new ArrayList<>(exception.getErrorCount());
        for (FieldError fieldError : exception.getFieldErrors())
            validationErrors.add(ValidationError.builder()
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue())
                    .code(fieldError.getCode())
                    .message(fieldError.getDefaultMessage()).build()
            );
        for (ObjectError globalError : exception.getGlobalErrors())
            validationErrors.add(ValidationError.builder()
                    .code(globalError.getCode())
                    .message(globalError.getDefaultMessage()).build()
            );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.VALIDATION_ERROR).validation(validationErrors).build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<ValidationError> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<?> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            errors.add(ValidationError.builder()
                    .field(field)
                    .rejectedValue(violation.getInvalidValue())
                    .code(violation.getMessageTemplate())
                    .message(violation.getMessage()).build()
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(ResponseCode.VALIDATION_ERROR).validation(errors).build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(exception.getMessage()));
    }

}
