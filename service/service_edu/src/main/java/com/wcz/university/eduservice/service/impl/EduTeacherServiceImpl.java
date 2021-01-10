package com.wcz.university.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcz.university.eduservice.pojo.EduTeacher;
import com.wcz.university.eduservice.mapper.EduTeacherMapper;
import com.wcz.university.eduservice.pojo.vo.TeacherQuery;
import com.wcz.university.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wczy
 * @since 2020-12-31
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper teacherMapper;

    /**
    * @Author Wczy
    * @Date 2021-01-05 14:47
    * @Param [current, commit, teacherQuery]
    * @Return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.wcz.university.eduservice.pojo.EduTeacher>
    * @description 分页逻辑查询讲师
    **/
    @Override
    public Page<EduTeacher> pageTeacherQuery(long current, long limit, TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            teacherWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            teacherWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            teacherWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            teacherWrapper.le("gmt_create",end);
        }
        //时间降序排列
        teacherWrapper.orderByDesc("gmt_create");
        teacherMapper.selectPage(teacherPage,teacherWrapper);
        return teacherPage;
    }

}
