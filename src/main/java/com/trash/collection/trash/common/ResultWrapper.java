package com.trash.collection.trash.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * web返回信息封装类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultWrapper<T> implements Serializable {
    private static final long serialVersionUID = -7282816602355656687L;

    /**
     * 成功码
     */
    private static final String SUCCESS_CODE = String.valueOf(200);

    /**
     * 成功信息
     */
    private static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码
     */
    private static final String ERROR_CODE = String.valueOf(500);

    /**
     * 错误信息
     */
    private static final String ERROR_MESSAGE = "内部异常";

    /**
     * 错误码：参数错误
     */
    private static final String ILLEGAL_ARGUMENT_CODE_ = String.valueOf(100);

    /**
     * 错误信息：参数错误
     */
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "非法参数";

    /**
     * 信息码
     */
    private String code;

    /**
     * 信息
     */
    private String message;

    /**
     * 结果数据
     */
    private T data;

    public static ResultWrapper success() {
        return new ResultWrapper<>(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> ResultWrapper<T> successMsg(String msg) {
        return new ResultWrapper<>(SUCCESS_CODE, msg, null);
    }

    public static <T> ResultWrapper<T> success(T data) {
        return new ResultWrapper<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }


    public static <T> ResultWrapper<T> error() {
        return new ResultWrapper<>(ERROR_CODE, ERROR_MESSAGE, null);
    }

    /**
     * 自定义错误内容
     *
     * @param msg 错误信息
     * @return ResultWrapper
     */
    public static <T> ResultWrapper<T> error(String msg) {
        return new ResultWrapper<>(ERROR_CODE, msg, null);
    }

    public static <E> ResultWrapper<E> wrap(String code, String message) {
        return wrap(code, message, null);
    }

    public static <E> ResultWrapper<E> wrap(String code, String message, E o) {
        return new ResultWrapper<>(code, message, o);
    }

    public static ResultWrapper error(GlobalException e) {
        return wrap(String.valueOf(e.getCode()), e.getMessage());
    }

    public static <E> ResultWrapper<E> error(ErrorCode errorCode) {
        return wrap(errorCode.getErrorCode(), errorCode.getMsg());
    }

    public static ResultWrapper error(ErrorCode errorCode, Object... value) {
        return wrap(errorCode.getErrorCode(), String.format(errorCode.getMsg(), value));
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Objects.equals(SUCCESS_CODE, this.getCode());
    }
}
