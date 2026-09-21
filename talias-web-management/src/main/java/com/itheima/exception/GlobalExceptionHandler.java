package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//全局异常处理类
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了",e);
        return Result.error("程序出错了");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(Exception e){
        log.error("程序出错了",e);
        String msg = e.getMessage();
        int i = msg.indexOf("Duplicate entry");
        String key = msg.substring(i);
        String[] arr = key.split(" ");
        return Result.error(arr[2] + "已存在");
    }

}
