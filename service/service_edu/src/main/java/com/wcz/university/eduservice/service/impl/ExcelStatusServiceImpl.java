package com.wcz.university.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcz.university.eduservice.pojo.ExcelStatus;
import com.wcz.university.eduservice.mapper.ExcelStatusMapper;
import com.wcz.university.eduservice.pojo.vo.ExcelQuery;
import com.wcz.university.eduservice.service.ExcelStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 表格 服务实现类
 * </p>
 *
 * @author wczy
 * @since 2021-01-08
 */
@Service
public class ExcelStatusServiceImpl extends ServiceImpl<ExcelStatusMapper, ExcelStatus> implements ExcelStatusService {

    @Autowired
    private ExcelStatusMapper excelMapper;

    @Override
    public Page<ExcelStatus> queryPage(long current, long limit, ExcelQuery excelQuery) {
        Page<ExcelStatus> statusPage = new Page<>(current, limit);
        QueryWrapper<ExcelStatus> wrapper = new QueryWrapper<>();
        String excelName = excelQuery.getName();
        Integer excelStatus = excelQuery.getStatus();
        String time = excelQuery.getTime();
        if (!StringUtils.isEmpty(excelName)){
            wrapper.like("path",excelName);
        }
        if (!StringUtils.isEmpty(excelStatus)){
            wrapper.eq("level",excelStatus);
        }
        if (!StringUtils.isEmpty(time)){
            wrapper.like("gmt_create",time);
        }
        wrapper.orderByDesc("gmt_create");
        excelMapper.selectPage(statusPage, wrapper);
        return statusPage;
    }
}
