package com.zlf.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/03/22/20:15
 * @Description:
 */
public interface OssService {
    /**
    * @Description:  阿里云oss文件上传
    * @Param: [file]
    * @return: java.lang.String 文件url
    * @Author: zlf
    * @Date: 2021/4/2
    */
    String  uploadFileAvatar(MultipartFile file) throws IOException;
    /**
    * @Description: 阿里云oss文件删除
    * @Param: [url] 文件的url
    * @return: void
    * @Author: zlf
    * @Date: 2021/4/2
    */
    void removeFile(String url);
}
