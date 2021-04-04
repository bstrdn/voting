package ru.bstrdn.voting.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> notFoundException(NotFoundException e) {
        log.error("{} (id: {})", e.getMessage(), e.getIdResource());
        Response response = new Response(e.getIdResource(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<Response> incorrectDataException(IncorrectDataException e) {
        log.error(e.getMessage());
        Response response = new Response(400, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
