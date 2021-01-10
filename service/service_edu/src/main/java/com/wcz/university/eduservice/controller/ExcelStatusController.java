package com.wcz.university.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcz.university.eduservice.pojo.ExcelStatus;
import com.wcz.university.eduservice.pojo.vo.ExcelQuery;
import com.wcz.university.eduservice.service.ExcelStatusService;
import com.wcz.university.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 表格 前端控制器
 * </p>
 *
 * @author wczy
 * @since 2021-01-08
 */
@Api(tags = "excel文件管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/excel")
public class ExcelStatusController {

    @Autowired
    private ExcelStatusService excelService;

    /**
    * @Author Wczy
    * @Date 2021-01-09 10:36
    * @Param []
    * @Return com.wcz.university.utils.Result
    * @description 查询excel上传状态
    **/
    @ApiOperation(value = "多条件分页查询")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "current",value = "当前页数",required = true,paramType = "path",dataType = "long"),
            @ApiImplicitParam(name = "limit",value = "每页记录数",required = true,paramType = "path",dataType = "long")
    })
    @PostMapping("queryPage/{current}/{limit}")
    public Result queryPage(@RequestBody(required = false) ExcelQuery excelQuery,
                            @PathVariable Long current, @PathVariable Long limit){
        Page<ExcelStatus> queryPage = excelService.queryPage(current, limit, excelQuery);
        long total = queryPage.getTotal();
        List<ExcelStatus> excelList = queryPage.getRecords();
        return Result.ok()
                .data("total",total)
                .data("rows",excelList);
    }
}

