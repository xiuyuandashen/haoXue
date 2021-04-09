package com.zlf.edu.fegin.fallback;

import com.zlf.commonutils.vo.ResultVo;
import com.zlf.edu.fegin.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/04/09/22:26
 * @Description: Oss远程调用服务的容错类 (服务熔断 本地备胎
 */
@Slf4j
@Service
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public ResultVo test() {
        return ResultVo.error();
    }

    @Override
    public ResultVo remove(String url) {
        log.info("熔断保护");
        return ResultVo.error();
    }
}
