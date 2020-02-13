package com.trash.collection.trash.common;

/**
 * 自定义异常
 *
 * @author seth
 * @since 2020-01-19
 */
public class RRException extends RuntimeException {
    // 状态码
    private StatusCode statusCode = StatusCode.System_Error;
    private int code = 500;
    private String message;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public RRException() {
        this.statusCode = StatusCode.System_Error;
    }

    public RRException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public RRException(StatusCode statusCode, String message) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public RRException(String message) {
        super(message);
        this.message = message;
    }

    public RRException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public RRException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
