package com.example.jwtdemo.payload.response;

public class ContentResponse {

    private String message;

    public ContentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
