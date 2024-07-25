package com.cvorotava.backend.error.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private ErrorCodeEnum errorCode;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone = "Europe/London")
    private Instant timestamp;
    private String message;
    @JsonIgnore
    private String debugMessage;
    private List<DefaultErrorSubResponse> subErrors;

    public ErrorResponse() {
        timestamp = Instant.now();
        errorCode = ErrorCodeEnum.ERR_DEFAULT_CODE;
    }

    public ErrorResponse(HttpStatus status, ErrorCodeEnum errorCode) {
        this();
        this.status = status;
        this.errorCode = errorCode;
    }

    public ErrorResponse(HttpStatus status, String message, ErrorCodeEnum errorCode, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        if (!ex.getMessage().isEmpty()) {
            this.debugMessage = "No further description";
        } else {
            this.debugMessage = ex.getCause().getMessage();
        }
    }
}
