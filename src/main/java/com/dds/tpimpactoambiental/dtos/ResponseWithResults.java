package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.dtos.response.Response;
import org.springframework.http.HttpStatus;

import java.util.List;


public class ResponseWithResults<T> extends Response {
    private List<T> results;

    public ResponseWithResults() {
    }

    public ResponseWithResults(HttpStatus status, List<T> results) {
        super(status);
        this.results = results;
    }

    public ResponseWithResults(HttpStatus status, String message, List<T> results) {
        super(status, message);
        this.results = results;
    }
}
