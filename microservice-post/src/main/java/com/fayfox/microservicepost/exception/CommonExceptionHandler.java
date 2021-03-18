package com.fayfox.microservicepost.exception;

import com.fayfox.dto.RespDTO;
import com.fayfox.exception.CommonException;
import com.fayfox.exception.validation.Field;
import com.fayfox.exception.validation.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class CommonExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<RespDTO> handleException(Exception e) {
        RespDTO resp = RespDTO.error("服务器异常");

        return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 在Controller参数里通过@Valid注解产生的异常
     * 如果使用BindingResult接收错误，则不会抛出这个异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Result result = new Result();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            Field field = new Field(error.getCode(), error.getField(), error.getDefaultMessage());
            result.addField(field);
        }
        RespDTO resp = RespDTO.validateError(result);
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }

    /**
     * Controller参数忘了加@RequestBody注解的时候，整个类都是空的，会走到这里
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<RespDTO> handleBindException(BindException e) {
        e.printStackTrace();
        Result result = new Result();
        for (FieldError error : e.getFieldErrors()) {
            Field field = new Field(error.getCode(), error.getField(), error.getDefaultMessage());
            result.addField(field);
        }
        RespDTO resp = RespDTO.validateError(result);
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RespDTO> handleConstraintViolationException(ConstraintViolationException e) {
        RespDTO resp = RespDTO.error(e.getMessage());
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }
}
