package application.controllers;

import application.exceptions.ExistsException;
import application.exceptions.NotFoundException;
import application.exceptions.WrongArgumentException;
import application.responses.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleNotFoundException(NotFoundException notFoundException) {
        return ErrorResponse.builder()
                .message(notFoundException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(WrongArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleWrongArgumentException(WrongArgumentException wrongArgumentException) {
        return ErrorResponse.builder()
                .message(wrongArgumentException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(ExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleExistsException(ExistsException existsException) {
        return ErrorResponse.builder()
                .message(existsException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }
}
