package com.example.BackendVolatile.util;

import com.example.BackendVolatile.util.constant.ResponseConstant;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ErrorMessage validExceptionHander(BindException be){
        List<FieldError> fieldError=be.getFieldErrors();
        StringBuffer stringBuffer=new StringBuffer();
        fieldError.forEach(s->stringBuffer.append(s.getDefaultMessage() + "!"));
        return new ErrorMessage(new ResultVO(ResponseConstant.FAILED, stringBuffer.toString()));
    }


    @ExceptionHandler
    @ResponseBody
    public ErrorMessage handle(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation violation : violations) {
            builder.append(violation.getMessage());
            break;
        }
        return new ErrorMessage(new ResultVO(ResponseConstant.GET_DATA_FAILED , builder.toString() + "!"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class ErrorMessage {
        private ResultVO response;
    }

}

