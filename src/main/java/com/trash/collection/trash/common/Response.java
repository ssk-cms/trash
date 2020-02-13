package com.trash.collection.trash.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 返回结果类
 *
 * @author seth
 * @since 2020-01-19
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    // 状态码
    protected Integer code;
    // 状态码
    protected StatusCode statusCode;
    // 状态信息
    protected String message;
    // 返回数据
    protected Object data;

    public Response() {
        this.statusCode = StatusCode.Success;
        this.code = this.statusCode.getValue();
        this.message = "操作成功";
    }

    public Response(Object data) {
        this();
        this.data = data;
    }

    public Integer getCode() {
        return statusCode.getValue();
    }

    public Response setCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public Response setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }
}
