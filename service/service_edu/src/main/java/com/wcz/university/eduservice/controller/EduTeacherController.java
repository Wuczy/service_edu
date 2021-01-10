package com.wcz.university.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcz.university.eduservice.pojo.EduTeacher;
import com.wcz.university.eduservice.pojo.vo.TeacherQuery;
import com.wcz.university.eduservice.service.EduTeacherService;
import com.wcz.university.utils.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wczy
 * @since 2020-12-31
 */
@Api(tags = "讲师管理")
@CrossOrigin//解决跨域
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
    * @Author Wczy
    * @Date 2020-12-31 12:48
    * @Param []
    * @Return java.util.List<com.wcz.university.eduservice.pojo.EduTeacher>
    * @description 查询所有讲师
    **/
    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("findAll")
    public Result findAll(){
        List<EduTeacher> teacherList = teacherService.list(null);
        return Result.ok()
                .data("items",teacherList);
    }

    /**
    * @Author Wczy
    * @Date 2020-12-31 13:03
    * @Param [id]
    * @Return boolean
    * @description 根据id逻辑删除讲师
    **/
    @ApiOperation(value = "根据id逻辑删除讲师")
    @ApiImplicitParam(name = "id",value = "讲师ID",paramType = "path",required = true)
    @DeleteMapping("{id}")
    public Result removeTeacher( @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag ? Result.ok() : Result.error();
    }

    /**
    * @Author Wczy
    * @Date 2020-12-31 15:38
    * @Param []
    * @Return com.wcz.university.utils.Result
    * @description 分页查询讲师
    **/
    @ApiOperation(value = "分页查询讲师")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current",value = "当前页数",required = true,paramType = "path",dataType = "long"),
        @ApiImplicitParam(name = "limit",value = "每页记录数",required = true,paramType = "path",dataType = "long")
    })
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current, @PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        teacherService.page(teacherPage,null);
        //总记录数
        long total = teacherPage.getTotal();
        //数据list集合
        List<EduTeacher> teachers = teacherPage.getRecords();
        return Result.ok()
                .data("total",total)
                .data("rows",teachers);
    }

    /**
    * @Author Wczy
    * @Date 2020-12-31 16:14
    * @Param [current, limit]
    * @Return com.wcz.university.utils.Result
    * @description
    **/
    @ApiOperation(value = "多条件分页查询")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "current",value = "当前页数",required = true,paramType = "path",dataType = "long"),
            @ApiImplicitParam(name = "limit",value = "每页记录数",required = true,paramType = "path",dataType = "long")
    })
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                       @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = teacherService.pageTeacherQuery(current, limit, teacherQuery);
        long total = teacherPage.getTotal();
        List<EduTeacher> teacherList = teacherPage.getRecords();
        return Result.ok()
                .data("total",total)
                .data("rows",teacherList);
    }

    /**
    * @Author Wczy
    * @Date 2020-12-31 16:47
    * @Param [teacher]
    * @Return com.wcz.university.utils.Result
    * @description 增加讲师的方法
    **/
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher teacher){
        boolean flag = teacherService.save(teacher);
        return flag ? Result.ok().data("data",teacher) : Result.error();
    }

    /**
    * @Author Wczy
    * @Date 2020-12-31 16:57
    * @Param [id]
    * @Return com.wcz.university.utils.Result
    * @description 根据id获取讲师信息
    **/
    @ApiOperation(value = "根据id获取讲师信息")
    @ApiImplicitParam(name = "id",value = "讲师ID",paramType = "path",required = true)
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return Result.ok()
                .data("data",teacher);
    }

    /**
    * @Author Wczy
    * @Date 2020-12-31 16:59
    * @Param [teacher]
    * @Return com.wcz.university.utils.Result
    * @description  修改讲师的方法
    **/
    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher teacher){
        boolean flag = teacherService.updateById(teacher);
        return flag ? Result.ok().data("data",teacher) : Result.error();
    }
}

