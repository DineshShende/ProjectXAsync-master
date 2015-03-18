package com.projectx.async.util.exception;

public class ResourceAlreadyPresentException extends RuntimeException{

	public ResourceAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public ResourceAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyPresentException(String message) {
        super(message);
    }

    public ResourceAlreadyPresentException() {
    }
	
}
