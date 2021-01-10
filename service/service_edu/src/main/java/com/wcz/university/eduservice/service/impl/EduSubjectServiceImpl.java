package com.wcz.university.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wcz.university.eduservice.listener.SubjectExcelListener;
import com.wcz.university.eduservice.mapper.ExcelStatusMapper;
import com.wcz.university.eduservice.pojo.EduSubject;
import com.wcz.university.eduservice.mapper.EduSubjectMapper;
import com.wcz.university.eduservice.pojo.ExcelStatus;
import com.wcz.university.eduservice.pojo.excel.SubjectData;
import com.wcz.university.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcz.university.servicebase.exception.ExceptionEnum;
import com.wcz.university.servicebase.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wczy
 * @since 2021-01-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper subjectMapper;
    @Autowired
    private ExcelStatusMapper excelMapper;


    /**
    * @Author Wczy
    * @Date 2021-01-06 22:23
    * @Param [excel]
    * @Return java.util.List<com.wcz.university.eduservice.pojo.EduSubject>
    * @description 利用easyExcel读取上传的文件
    **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSubject(MultipartFile excel) {
        if (Objects.isNull(excel)){
            throw ExceptionUtil.getException(ExceptionEnum.FILE_UPLOAD_ERROR_02);
        }
        String filename = excel.getOriginalFilename();
        if (StringUtils.isEmpty(filename)){
            throw ExceptionUtil.getException(ExceptionEnum.EXCEL_UPLOAD_ERROR_01);
        }
        String type = filename.substring(filename.lastIndexOf("."));
        if(!type.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) &&
            !type.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())){
            throw ExceptionUtil.getException(ExceptionEnum.EXCEL_UPLOAD_ERROR_03);
        }
        try {
            ExcelStatus excelStatus = new ExcelStatus();
            excelStatus.setName(filename);
            InputStream inputStream = excel.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectMapper,excelMapper,excelStatus))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            ExcelStatus excelStatus = new ExcelStatus();
            excelStatus.setName(filename);
            excelStatus.setStatus(0);
            excelMapper.insert(excelStatus);
            throw ExceptionUtil.getException(ExceptionEnum.SUBJECT_ADD_ERROR_01);
        }

    }
}
