package com.projectx.async.util.exception;

public class ValidationFailedException extends RuntimeException{
	
	 public ValidationFailedException(Throwable cause) {
	        super(cause);
	    }

	    public ValidationFailedException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public ValidationFailedException(String message) {
	        super(message);
	    }

	    public ValidationFailedException() {
	    }


}
