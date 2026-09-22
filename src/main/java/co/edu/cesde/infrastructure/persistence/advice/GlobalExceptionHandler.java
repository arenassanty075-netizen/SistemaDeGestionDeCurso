package co.edu.cesde.infrastructure.persistence.advice;

import co.edu.cesde.application.exception.ResouceConflicException;
import co.edu.cesde.application.exception.ResouceNotFoundException;
import co.edu.cesde.application.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExists(ResourceAlreadyExistsException e) {

        String message = e.getCause()!=null ? e.getCause().getMessage() : e.getMessage();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(message);

    }

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<String> handleResouceNotFoundException(ResouceNotFoundException e){
        String message = e.getCause()!=null ? e.getCause().getMessage() : e.getMessage();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleResouceConflicException(ResouceConflicException e){
        String message = e.getCause()!=null ? e.getCause().getMessage() : e.getMessage();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(message);
    }
}
