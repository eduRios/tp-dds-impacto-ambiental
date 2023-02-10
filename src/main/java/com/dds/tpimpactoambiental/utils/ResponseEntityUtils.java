package com.dds.tpimpactoambiental.utils;

import com.dds.tpimpactoambiental.dtos.response.Response;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

    public static <T extends Response> ResponseEntity<T> toResponseEntity(T response) {
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
