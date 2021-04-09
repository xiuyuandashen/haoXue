package com.zlf.edu.controller;


import com.zlf.commonutils.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
* @Description: 登录接口
* @Author: zlf
* @Date: 2021/3/22
*/
@Api(tags = "模拟登录接口")
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin // 跨域注解
public class EduLoginController {


    @PostMapping("login")
    public ResultVo login(){
        return ResultVo.ok().data("token","admin");
    }

    @PostMapping("info")
    public ResultVo info(){
        return ResultVo.ok().data("roles","[admin]").data("name","admin").data("avatar","https://cdn.jsdelivr.net/gh/xiuyuandashen/figureBed/2021/02/17c53e8bd5ecf64a288ecd7b7070250098.png");
    }
}
