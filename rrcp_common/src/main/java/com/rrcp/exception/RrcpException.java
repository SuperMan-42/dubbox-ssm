package com.rrcp.exception;
/**
 * 
 * @author yingjun
 *
 */
public class RrcpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RrcpException(String message) {
        super(message);
    }

    public RrcpException(String message, Throwable cause) {
        super(message,  cause);
    }

}
