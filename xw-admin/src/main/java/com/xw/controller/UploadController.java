package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.sevice.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author 小吴
 * @Date 2023/06/03 21:30
 * @Version 1.0
 */

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /*
    上传文件接口
     */
    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) {
        try {
            return uploadService.uploadImg(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传上传失败");
        }
    }
}
