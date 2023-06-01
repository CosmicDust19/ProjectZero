package me.projectzero.infrastructure.exceptionhandlers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import me.projectzero.infrastructure.enumeration.ResponseCode;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseError;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Locale;

@RequiredArgsConstructor
@ControllerAdvice
@Order(0)
public class PersistenceExceptionHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).code(ResponseCode.ENTITY_NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).code(ResponseCode.ENTITY_NOT_FOUND).message(exception.getMessage()).build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ResponseCode code = exception.getMessage().extract("(fk|uk)_\\w+_\\w+").map(constraint -> ResponseCode.valueOf(constraint.toUpperCase(Locale.ENGLISH))).orElse(ResponseCode.INVALID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponseError.builder().throwable(exception).message(code).build());
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Object> handlePersistenceException(PersistenceException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceResponseError.builder().throwable(exception).message(exception.getMessage()).build());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceResponseError.builder().throwable(exception).message(exception.getMessage()).build());
    }

}
