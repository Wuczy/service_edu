package com.wcz.university.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcz.university.eduservice.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcz.university.eduservice.pojo.vo.TeacherQuery;
/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wczy
 * @since 2020-12-31
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /*
    * 分页逻辑查询讲师
    */
     Page<EduTeacher> pageTeacherQuery(long current, long limit, TeacherQuery teacherQuery);
}
