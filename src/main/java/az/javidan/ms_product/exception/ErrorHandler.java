package az.javidan.ms_product.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.javidan.ms_product.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static az.javidan.ms_product.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex){
        log.error("Exception: ", ex);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE,UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle (NotFoundException ex){
        log.error("Exception: ", ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }
}
