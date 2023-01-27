package com.dds.tpimpactoambiental.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class Response {

    @JsonIgnore
    private HttpStatus status;
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public Response() {
    }

    public Response(HttpStatus status) {
        this.status = status;
    }

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response message(String message){
        this.message = message;
        return this;
    }
}
