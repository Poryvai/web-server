package com.exception;

import com.server.request.StatusCode;

public class ServerException extends RuntimeException {
    private StatusCode statusCode;

    public ServerException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public ServerException(String message, Throwable cause, StatusCode statusCode){
        super(message, cause);
        this.statusCode = statusCode;
    }
    public StatusCode getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
