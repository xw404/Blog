package com.xw.sevice;

import com.xw.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /*
    图片上传
     */
    ResponseResult uploadImg(MultipartFile img);
}