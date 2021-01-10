package com.wcz.university.servicebase.exception;

import com.wcz.university.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @ProjectName: service_edu
 * @ClassName: GlobalExceptionHandler
 * @Auther: wczy
 * @Date: 2020-12-31 17:11
 * @Version 1.0 统一异常处理
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定执行了什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error()
                .code(20001)
                .message("系统异常！");
    }

    //指定执行了什么异常执行这个方法
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public Result error(MaxUploadSizeExceededException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error()
                .code(20001)
                .message("上传文件超过10M！");
    }

    //自定义异常，需要自己手动去抛出
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error()
                .code(e.getCode())
                .message(e.getMsg());
    }



}
