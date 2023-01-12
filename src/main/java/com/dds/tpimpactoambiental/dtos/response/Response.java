package com.dds.tpimpactoambiental.dtos.response;

public class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    public Response() {

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
