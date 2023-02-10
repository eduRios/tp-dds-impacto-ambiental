package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.dtos.response.Response;
import org.springframework.http.HttpStatus;


public class ResponseWithSingleResult<T> extends Response {
    private T result;

    public ResponseWithSingleResult(HttpStatus status, T result) {
        super(status);
        this.result = result;
    }

    public ResponseWithSingleResult(HttpStatus status, String message, T result) {
        super(status, message);
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
