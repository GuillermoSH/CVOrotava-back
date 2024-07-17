package com.cvorotava.backend.error.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class DefaultErrorSubResponse {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
