package com.DemoProject.Entity;

public class ApiResponse {
    private String message;
    private Object data;
    private int statusCode;

    public ApiResponse(String message, Object data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}

