package com.server.request;

public enum StatusCode {
    OK(200, "Ok"),
    NOT_FOUND(404, "Not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    BAD_REQUEST(400, "Bad request"),
    METHOD_NOT_ALLOWED(405,"Method not allowed");

    private final int code;
    private final String statusText;

    StatusCode(int code, String statusTextt){
        this.code = code;
        this.statusText = statusTextt;
    }

    public int getCode() {
        return code;
    }

    public String getStatusText() {
        return statusText;
    }
}
