package com.wcz.university.eduservice.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 表格
 * </p>
 *
 * @author wczy
 * @since 2021-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ExcelStatus对象", description="表格")
public class ExcelStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表格id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "表格名字")
    @TableField(value = "path")
    private String name;

    @ApiModelProperty(value = "状态 1上传成功 2上传失败")
    @TableField(value = "level")
    private Integer status;

    @ApiModelProperty(value = "表格行数")
    private Integer line;

    @ApiModelProperty(value = "一级课程数量")
    private Integer firstClass;

    @ApiModelProperty(value = "二级课程数量")
    private Integer secondClass;

    @ApiModelProperty(value = "上传时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
