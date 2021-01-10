package com.wcz.university.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcz.university.eduservice.pojo.ExcelStatus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcz.university.eduservice.pojo.vo.ExcelQuery;


/**
 * <p>
 * 表格 服务类
 * </p>
 *
 * @author wczy
 * @since 2021-01-08
 */
public interface ExcelStatusService extends IService<ExcelStatus> {

    Page<ExcelStatus> queryPage(long current, long limit, ExcelQuery excelQuery);
}
