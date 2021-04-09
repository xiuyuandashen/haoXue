package com.zlf.edu.fegin;

import com.zlf.commonutils.vo.ResultVo;
import com.zlf.edu.fegin.fallback.OssFileServiceFallBack;
import org.apache.ibatis.annotations.Delete;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/03/31/21:13
 * @Description: Oss 提供给edu的远程调用
 */
@FeignClient(name = "service-oss" ,fallback = OssFileServiceFallBack.class) // nacos 中注册的服务名称  fallback 容错类
public interface OssFileService {

    @GetMapping("/eduOss/fileOss/test")
    ResultVo test();

    /**
    * @Description: 从oss中删除文件
    * @Param: [url]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/4/9
    */
    @DeleteMapping("/eduOss/fileOss/remove")
    ResultVo remove(@RequestBody String url);
}
