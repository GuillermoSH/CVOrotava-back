package com.cvorotava.backend.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class DeleteOperationException extends RuntimeException {
    public DeleteOperationException() {
        super();
    }

    public DeleteOperationException(String message) {
        super(message);
    }

    public DeleteOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteOperationException(Throwable cause) {
        super(cause);
    }
}
