package com.wcz.university.eduservice.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ProjectName: service_edu
 * @ClassName: SubjectData
 * @Auther: wczy
 * @Date: 2021-01-06 22:24
 * @Version 1.0 excel对应的实体类
 **/
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String firstName;

    @ExcelProperty(index = 1)
    private String secondName;

}
