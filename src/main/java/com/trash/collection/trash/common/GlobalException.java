package com.trash.collection.trash.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Owen on 2019-08-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 5409357787496133569L;

    private String code;

    public GlobalException() {
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(String code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public GlobalException(ErrorCode errorCode, Object... args) {
        super(String.format(errorCode.getMsg(), args));
        this.code = errorCode.getErrorCode();
    }
}
