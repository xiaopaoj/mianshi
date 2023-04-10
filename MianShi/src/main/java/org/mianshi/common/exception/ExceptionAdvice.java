package org.mianshi.common.exception;

import com.google.common.collect.ImmutableMap;
import org.mianshi.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeoutException;

/**
 * 全局异常捕获冰封装返回信息
 * @author : nate.jiang
 * @date : 2021/9/2 10:57
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    private static final ImmutableMap<Class<? extends Throwable>, BaseEnum> EXCEPTION_MAPPINGS;

    static {
        final ImmutableMap.Builder<Class<? extends Throwable>, BaseEnum> builder = ImmutableMap.builder();
        // SpringMVC中参数类型转换异常，常见于String找不到对应的ENUM而抛出的异常
        builder.put(MethodArgumentTypeMismatchException.class, ServerCode.INVALID_REQUEST);
        builder.put(UnsatisfiedServletRequestParameterException.class, ServerCode.INVALID_REQUEST);
        // HTTP Request Method不存在
        builder.put(HttpRequestMethodNotSupportedException.class, ServerCode.NOT_FOUND);
        // 要求有RequestBody的地方却传入了NULL
        builder.put(HttpMessageNotReadableException.class, ServerCode.WRONG_PARAM);
        builder.put(IllegalArgumentException.class, ServerCode.WRONG_PARAM);
        builder.put(TimeoutException.class,ServerCode.WRONG_PARAM);
        // 其他未被发现的异常
        builder.put(Exception.class, ServerCode.INTERNAL_SERVER_ERROR);
        EXCEPTION_MAPPINGS = builder.build();
    }

    @ResponseBody
    @ExceptionHandler(value = {BaseException.class})
    public ResponseResult baseException(BaseException e, HttpServletResponse response) {
        LOGGER.error("BaseException {}", e.getDesc());
        response.setStatus(HttpStatus.ACCEPTED.value());
        return ResponseResult.error(ResponseResult.ERROR_BUSINESS_STATUS_CODE,
                e.getDesc());
    }

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseResult runtimeException(RuntimeException e, HttpServletResponse response) {
        LOGGER.error("runtimeException", e);
        BaseEnum status;
        if(e.getCause() != null ){
            status = EXCEPTION_MAPPINGS.get( e.getCause().getClass());
        }else{
            status = EXCEPTION_MAPPINGS.get( e.getClass());
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
        if (status != null) {
            return ResponseResult.error(status.getCode(), status.getDesc());
        } else {
            return ResponseResult.error(ResponseResult.ERROR_BUSINESS_STATUS_CODE, "操作异常");
        }
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("methodArgumentNotValidException: {}", e.getMessage());
        String message = "";
        if(null != e.getBindingResult().getFieldError()) {
            message = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ResponseResult.error(ResponseResult.ERROR_BUSINESS_STATUS_CODE, message);
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseResult httpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("methodArgumentNotValidException: {}", e.getMessage());
        return ResponseResult.error(ResponseResult.ERROR_BUSINESS_STATUS_CODE, "请求参数格式非法");
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseResult exception(Exception e, HttpServletResponse response) {
        LOGGER.error("exception", e);
        response.setStatus(HttpStatus.ACCEPTED.value());
        return ResponseResult.error(ResponseResult.ERROR_BUSINESS_STATUS_CODE, "操作异常");
    }
}
