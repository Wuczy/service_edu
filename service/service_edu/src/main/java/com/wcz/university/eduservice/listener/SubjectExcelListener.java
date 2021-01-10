package com.wcz.university.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcz.university.eduservice.mapper.EduSubjectMapper;
import com.wcz.university.eduservice.mapper.ExcelStatusMapper;
import com.wcz.university.eduservice.pojo.EduSubject;
import com.wcz.university.eduservice.pojo.ExcelStatus;
import com.wcz.university.eduservice.pojo.excel.SubjectData;
import com.wcz.university.servicebase.exception.ExceptionEnum;
import com.wcz.university.servicebase.exception.ExceptionUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @ProjectName: service_edu
 * @ClassName: SubjectExcelListener
 * @Auther: wczy
 * @Date: 2021-01-06 22:29
 * @Version 1.0 subject excel文件读取监听器
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //SubjectExcelListener不能交给spring进行管理，手动注入mapper
    private EduSubjectMapper subjectMapper;
    private ExcelStatusMapper statusMapper;
    private ExcelStatus excelStatus;

    private int lines;
    private int firstClass;
    private int secondClass;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectMapper subjectMapper,ExcelStatusMapper statusMapper,ExcelStatus excelStatus) {
        this.subjectMapper = subjectMapper;
        this.statusMapper = statusMapper;
        this.excelStatus = excelStatus;
    }

    /**
    * @Author Wczy
    * @Date 2021-01-06 22:34
    * @Param [eduSubject, analysisContext]
    * @Return void
    * @description 一条一条读取excel
    **/
    //@Transactional
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        lines++;
        EduSubject eduFirstSubject = exitFirstName(subjectData.getFirstName());
        //添加一级分类
        if (eduFirstSubject == null){
            eduFirstSubject = new EduSubject();
            eduFirstSubject.setTitle(subjectData.getFirstName());
            eduFirstSubject.setParentId("0");
            subjectMapper.insert(eduFirstSubject);
            firstClass++;
        }
        String pid = eduFirstSubject.getId();
        EduSubject eduSecondSubject = exitSecondName(subjectData.getSecondName(), pid);
        //添加二级分类
        if (eduSecondSubject == null){
            eduSecondSubject = new EduSubject();
            eduSecondSubject.setTitle(subjectData.getSecondName());
            eduSecondSubject.setParentId(pid);
            subjectMapper.insert(eduSecondSubject);
            secondClass++;
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //todo if(lines == 0)????
        excelStatus.setLine(lines);
        excelStatus.setFirstClass(firstClass);
        excelStatus.setSecondClass(secondClass);
        excelStatus.setStatus(1);
        statusMapper.insert(excelStatus);
    }

    /**
    * @Author Wczy
    * @Date 2021-01-06 23:02
    * @Param [name]
    * @Return com.wcz.university.eduservice.pojo.EduSubject
    * @description 判断一级分类不能重复添加
    **/
    private EduSubject exitFirstName(String name){
        if (name == null){
            throw ExceptionUtil.getException(ExceptionEnum.SUBJECT_ADD_ERROR_02);
        }
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>()
                .eq("parent_id","0")
                .eq("title",name);
        return subjectMapper.selectOne(wrapper);
    }

    /**
    * @Author Wczy
    * @Date 2021-01-06 23:34
    * @Param [name, pid]
    * @Return com.wcz.university.eduservice.pojo.EduSubject
    * @description 判断二级分类不能重复添加
    **/
    private EduSubject exitSecondName(String name,String pid){
        if (name == null){
            throw ExceptionUtil.getException(ExceptionEnum.SUBJECT_ADD_ERROR_03);
        }
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>()
                .eq("title",name)
                .eq("parent_id",pid);
        return subjectMapper.selectOne(wrapper);
    }
}
