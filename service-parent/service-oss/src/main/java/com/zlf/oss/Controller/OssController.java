package com.zlf.oss.Controller;


import com.zlf.commonutils.vo.ResultVo;
import com.zlf.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/03/22/20:16
 * @Description: 阿里云oss  文件管理
 */
@RestController
@RequestMapping("/eduOss/fileOss")
@CrossOrigin
@Api(tags = "oss上传接口")
@Slf4j
public class OssController {


    @Autowired
    private OssService ossService;

    @Value("${server.port}")
    String port;

    /**
    * @Description: 上传头像
    * @Param: [file]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/22
    */
    @PostMapping("uploadFile")
    @ApiOperation("上传文件")
    public ResultVo uploadOssFile(@ApiParam(name = "file",value = "文件") MultipartFile file) throws IOException {
        //获取上传对象
        String url = null;
        // 在 uploadFileAvatar 中 已经做了异常处理
        url = ossService.uploadFileAvatar(file);
        //  这一步已经在  uploadFileAvatar 完成了
        if(url == null) return ResultVo.error().message("文件上传失败");
        return ResultVo.ok().message("文件上传成功").data("url",url);
    }

    @ApiOperation("测试远程调用接口(提供调用)")
    @GetMapping("test")
    public ResultVo test(){
        System.out.println("我被调用的--- --");
        log.info("我被调用了{}",port);
        try{
            // 沉睡三秒
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return ResultVo.ok().message("测试远程调用");
    }

    @ApiOperation("文件删除")
    @DeleteMapping("remove")
    public ResultVo removeFile(@ApiParam(name = "url",value = "文件url") @RequestBody String url){
        //System.out.println(url);
        ossService.removeFile(url);
        return ResultVo.ok();
    }


}
