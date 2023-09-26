package ru.altunin.bracketschecker.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.altunin.bracketschecker.models.dto.ErrorResponse;

@Log4j
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = PreparationTextError.class)
    public ResponseEntity<ErrorResponse> handleIEmptyTextException(PreparationTextError e) {
        log.error(e.getMessage() + "\n");
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}