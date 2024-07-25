package com.cvorotava.backend.error.handler;

import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.error.pojo.ErrorCodeEnum;
import com.cvorotava.backend.error.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.BindException;
import java.sql.SQLIntegrityConstraintViolationException;

@Order
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(value = { NoContentException.class })
    protected ResponseEntity<Object> handleNoContentException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "The requested list has not got any registry on the data base";
        ErrorResponse error = new ErrorResponse(HttpStatus.NO_CONTENT, bodyOfResponse, ErrorCodeEnum.ERR_NO_DATA_CODE, ex);
        return buildResponseEntity(error);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "The requested entity could not be found on the data base";
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, bodyOfResponse, ErrorCodeEnum.ERR_NOT_FOUND_CODE, ex);
        return buildResponseEntity(error);
    }

    @ExceptionHandler(value = { InternalServerException.class })
    protected ResponseEntity<Object> handleInternalServerException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Something went wrong with the request";
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse, ErrorCodeEnum.ERR_INT_SERVER_CODE, ex);
        return buildResponseEntity(error);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<Object> handleInvalidArgumentException(Exception ex, WebRequest request) {
        String bodyOfResponse = "Some of the fields aren't valid";
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, bodyOfResponse, ErrorCodeEnum.ERR_BAD_REQUEST, ex);
        return buildResponseEntity(error);
    }

    @ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
    protected ResponseEntity<Object> handleUniqueConstraintException(Exception ex, WebRequest request) {
        String bodyOfResponse = "Constraint violation on the insertion to the data base";
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse, ErrorCodeEnum.ERR_INT_CONSTRAINT_CODE, ex);
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        log.error(errorResponse.getDebugMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
