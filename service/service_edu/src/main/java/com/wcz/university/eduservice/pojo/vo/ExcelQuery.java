package com.wcz.university.eduservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName: service_edu
 * @ClassName: ExcelQuery
 * @Auther: wczy
 * @Date: 2021-01-09 10:29
 * @Version 1.0
 **/
@ApiModel(value = "excel查询对象", description = "表格查询对象封装")
@Data
public class ExcelQuery {

    @ApiModelProperty(value = "excel名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "状态 1上传成功 2上传失败")
    private Integer status;

    @ApiModelProperty(value = "上传时间", example = "2019-12-01")
    private String time;
}
