package com.trash.collection.trash.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Owen on 2019-08-06
 */
@Data
@Builder
public class ErrorCode implements Serializable {
    private static final long serialVersionUID = -5596590718270355538L;

    /**
     * 服务码
     */
    private int serviceCode;

    /**
     * 错误级别
     */
    private int errorLevel;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    public String getErrorCode() {
        return serviceCode + String.format("%04d", code) +
                errorLevel;
    }

    public String getFormatResultMsg(Object... value) {
        return String.format(msg, value);
    }

}
