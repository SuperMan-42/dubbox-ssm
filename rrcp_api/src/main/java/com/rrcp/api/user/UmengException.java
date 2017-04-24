package com.rrcp.api.user;

/**
 * Created by Hpw on 2017/4/24.
 */
public class UmengException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UmengException(String message) {
        super(message);
    }

    public UmengException(String message, Throwable cause) {
        super(message, cause);
    }
}
