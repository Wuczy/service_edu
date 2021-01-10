package com.wcz.university.oss.controller;

import com.wcz.university.oss.service.FileService;
import com.wcz.university.utils.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: service_edu
 * @ClassName: FileController
 * @Auther: wczy
 * @Date: 2021-01-04 18:57
 * @Version 1.0 阿里云文件管理
 **/
@Api(tags = "阿里云文件管理")
@CrossOrigin//跨域
@RestController
@RequestMapping("/ossservice/file")
public class FileController {
    @Autowired
    private FileService fileService;


    @ApiOperation("上传文件")
    @PostMapping("upload")
    public Result upload(@ApiParam(name = "file", value = "文件", required = true)
                             @RequestParam(value = "file") MultipartFile file){
        String uploadUrl = fileService.upload(file);
        return Result.ok()
                .message("上传成功")
                .data("url",uploadUrl);
    }
}
