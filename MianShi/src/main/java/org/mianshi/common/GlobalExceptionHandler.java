package org.mianshi.common;

import org.mianshi.common.exception.ServerCode;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 14:15
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 用来处理bean validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public  ResponseResult resolveConstraintViolationException(ConstraintViolationException ex){
        ResponseResult errorWebResult = ResponseResult.error(ServerCode.WRONG_PARAM);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            errorWebResult.setMessage(errorMessage);
            return errorWebResult;
        }
        errorWebResult.setMessage(ex.getMessage());
        return errorWebResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ResponseResult errorWebResult = ResponseResult.error(ServerCode.WRONG_PARAM);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            errorWebResult.setMessage(errorMessage);
            return errorWebResult;
        }
        errorWebResult.setMessage(ex.getMessage());
        return errorWebResult;
    }
}
