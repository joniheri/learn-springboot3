package com.jonheri.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private String status;
    private String message;
    private T data;
    private Map<String, List<String>> errors;

    public ResponseDTO(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(String status, String message, T data, Map<String, List<String>> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

}
