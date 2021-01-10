package com.wcz.university.servicebase.exception;

/**
 * @ProjectName: service_edu
 * @ClassName: ExceptionUtil
 * @Auther: wczy
 * @Date: 2021-01-04 16:01
 * @Version 1.0 异常处理类
 **/
public class ExceptionUtil {
    public static MyException getException(ExceptionEnum exceptionEnum){
        return new MyException(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }
}
