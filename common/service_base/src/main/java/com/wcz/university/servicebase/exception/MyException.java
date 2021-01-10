package com.wcz.university.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: service_edu
 * @ClassName: MyException
 * @Auther: wczy
 * @Date: 2021-01-01 10:22
 * @Version 1.0 自定义异常
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{
    //状态码
    private Integer code;
    //异常信息
    private String msg;
}
