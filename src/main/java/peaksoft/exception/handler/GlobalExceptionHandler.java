package peaksoft.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import peaksoft.exception.*;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e){
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .exceptionClassName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse alreadyExistException(AlreadyExistException e){
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .exceptionClassName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequestException(BadRequestException e){
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClassName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(BadCredentialException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse badCredentialException(BadCredentialException e){
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .exceptionClassName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Access denied message", HttpStatus.FORBIDDEN);
    }
}
