package me.projectzero.api.exceptionhandlers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import me.projectzero.domain.enumeration.ErrorCode;
import me.projectzero.domain.valueobject.DbConstraint;
import me.projectzero.infrastructure.common.util.StringUtils;
import me.projectzero.service.error.ServiceErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Order(0)
@ControllerAdvice
public class PersistenceExceptionHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).code(ErrorCode.ENTITY_NOT_FOUND).message(exception.getMessage()).wrap());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).code(ErrorCode.ENTITY_NOT_FOUND).message(exception.getMessage()).wrap());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorCode code = StringUtils.extract(exception.getMessage(), "(fk|uk)_\\w+_\\w+").map(DbConstraint::getErrorCode).orElse(ErrorCode.DATA_INTEGRITY_VIOLATION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(code).wrap());
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Object> handlePersistenceException(PersistenceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(exception.getMessage()).wrap());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ServiceErrorResponse.thrown(exception).message(exception.getMessage()).wrap());
    }

}
