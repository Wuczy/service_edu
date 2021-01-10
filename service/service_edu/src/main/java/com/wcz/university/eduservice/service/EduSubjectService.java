package com.wcz.university.eduservice.service;

import com.wcz.university.eduservice.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wczy
 * @since 2021-01-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile excel);
}
