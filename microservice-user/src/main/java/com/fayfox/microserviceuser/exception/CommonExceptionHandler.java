package com.fayfox.microserviceuser.exception;

import com.fayfox.dto.RespDTO;
import com.fayfox.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<RespDTO> handleException(Exception e) {
        RespDTO resp = RespDTO.error("服务器异常");

        return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
