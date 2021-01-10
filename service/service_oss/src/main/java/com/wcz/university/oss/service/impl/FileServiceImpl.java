package com.wcz.university.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.wcz.university.oss.service.FileService;
import com.wcz.university.oss.utils.ConstantPropertiesUtil;
import com.wcz.university.servicebase.exception.ExceptionEnum;
import com.wcz.university.servicebase.exception.ExceptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @ProjectName: service_edu
 * @ClassName: EduApplication
 * @Auther: wczy
 * @Date: 2020-01-04 15:09
 * @Version 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    //文件类型
    private static final Set<String> types = new HashSet<>();
    static {
        types.add("png");
        types.add("jpg");
        types.add("gif");
    }
    /**
    * @Author Wczy
    * @Date 2021-01-04 15:38
    * @Param [file]
    * @Return java.lang.String
    * @description oss上传文件
    **/
    @Override
    public String upload(MultipartFile file) {
        if (file == null){
            throw ExceptionUtil.getException(ExceptionEnum.FILE_UPLOAD_ERROR_02);
        }
        String filename = file.getOriginalFilename();
        if (StringUtils.isEmpty(filename)){
            throw ExceptionUtil.getException(ExceptionEnum.FILE_UPLOAD_ERROR_03);
        }
        //获取文件类型
        String type = filename.substring(filename.lastIndexOf(".")+1);
        if (!types.contains(type)){
            throw ExceptionUtil.getException(ExceptionEnum.FILE_UPLOAD_ERROR_04);
        }
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;
        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建bucket
            ossClient.createBucket(bucketName);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }
        InputStream inputStream;
        //获取上传文件
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
           throw ExceptionUtil.getException(ExceptionEnum.FILE_UPLOAD_ERROR_01);
        }
        //文件名：uuid.扩展名
        String original = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString();
        String fileType =original.substring(original.lastIndexOf("."));
        String newName = fileName + fileType;
        String fileUrl = fileHost + "/" + newName;
        //文件上传至阿里云
        ossClient.putObject(bucketName, fileUrl, inputStream);
        //关闭OSSClient
        ossClient.shutdown();
        //返回url地址
        return "https://" + bucketName + "." + endPoint + "/" + fileUrl;
    }


}