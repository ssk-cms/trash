package com.trash.collection.trash.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.Locale;
import java.util.Objects;

/**
 * 全局异常处理类
 *
 * @author seth
 * @since 2020-01-19
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource resources;

    /**
     * 自定义异常
     *
     * @param e
     */
    @ExceptionHandler(RRException.class)
    public Response RRExceptionHandler(RRException e) {
        Response response = new Response();
        if (e instanceof RRException) {
            response.setCode(e.getStatusCode());
        }
        Locale locale = Locale.CHINESE;
        String msg;
        if (e.getMessage() != null) {
            msg = this.resources.getMessage("none", null, e.getMessage(), locale);
        } else {
            msg = this.resources.getMessage(response.getCode().toString(), null, e.getStatusCode().name(), locale);
        }
        response.setMessage(msg);
        logger.error(e.getMessage(), e);
        return response;
    }

    /**
     * @param e 文件上传异常
     */
    @ExceptionHandler(MultipartException.class)
    public Response MultipartExceptionHandle(MultipartException e) {
        Response response = new Response();
        // 文件上传
        if (e instanceof MultipartException) {
            RRException ex = new RRException(StatusCode.File_Upload_Fail);
            response.setCode(ex.getStatusCode());
        }
        Locale locale = Locale.CHINESE;
        String msg;
        if (e.getMessage() != null) {
            msg = this.resources.getMessage("none", null, e.getMessage(), locale);
        } else {
            msg = this.resources.getMessage(response.getCode().toString(), null, e.getMessage(), locale);
        }
        response.setMessage(msg);
        logger.error("文件上传错误{}", e.getMessage());
        return response;
    }

    /**
     * 数据重复
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Response DuplicateKeyExceptionHandle(DuplicateKeyException e) {
        Response response = new Response();
        response.setCode(StatusCode.Data_Exist_Try_Again);
        String result = e.getCause().getLocalizedMessage();
        if (result.contains("username") || result.contains("user_name")) {
            response.setMessage("用户名已存在");
        } else if (result.contains("id_card")) {
            response.setMessage("身份证已存在");
        } else if (result.contains("phone")) {
            response.setMessage("手机号已存在");
        } else if (result.contains("license_number")) {
            response.setMessage("车牌号已存在");
        } else if (result.contains("vin")) {
            response.setMessage("车架号已存在");
        } else if (result.contains("engine_number")) {
            response.setMessage("发动机号已存在");
        } else if (result.contains("lease_code")) {
            response.setMessage("政策编号已存在");
        } else {
            response.setMessage("系统已存在该记录");
        }
        logger.error("系统已存在该记录{}", e.getMessage());
        logger.error(e.getMessage(), e);
        return response;
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error(e.getMessage(), e);

        Response response = new Response();
        response.setCode(StatusCode.Data_Error)
                .setStatusCode(StatusCode.Data_Error);
        if (Objects.isNull(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())){
            response.setMessage("请将相关内容填写完整");
        }
        else {
            response.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        return response;
    }

    /**
     * 系统异常
     *
     * @param e
     */
    @ExceptionHandler({Exception.class, Error.class, Throwable.class})
    public Response ErrorHandler(Throwable e) {
        Response response = new Response();
        response.setCode(StatusCode.System_Error);
        response.setMessage("系统错误，请联系管理员");
        logger.error(e.getMessage(), e);
        return response;
    }

}
