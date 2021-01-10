package com.wcz.university.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /*
    上传文件到oss
     */
    String upload(MultipartFile file);
}
