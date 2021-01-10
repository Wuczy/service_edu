package com.wcz.university.eduservice.controller;


import com.wcz.university.eduservice.service.EduSubjectService;
import com.wcz.university.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wczy
 * @since 2021-01-06
 */
@Api(tags = "课程分类")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    /**
    * @Author Wczy
    * @Date 2021-01-06 22:20
    * @Param [excel]
    * @Return com.wcz.university.utils.Result
    * @description excel表格添加课程分类
    **/
    @ApiOperation(value = "添加课程")
    @PostMapping("addSubject")
    public Result addSubject(@ApiParam(name = "file", value = "文件", required = true)
                                 @RequestParam(value = "file") MultipartFile excel){
        subjectService.addSubject(excel);
        return Result.ok()
                .message("批量导入课程成功");
    }

}

