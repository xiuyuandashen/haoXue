package com.zlf.edu.controller;



import com.zlf.commonutils.vo.ResultVo;
import com.zlf.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zlf
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
@Api(tags = "课程管理")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    /**
    * @Description: 通过上传excel，添加课程分类
    * @Param: [file]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @PostMapping("addSubject")
    public ResultVo addSubject(@ApiParam(name = "file",value = "上传excel") MultipartFile file){

        subjectService.saveSubject(file,subjectService);
        return ResultVo.ok();
    }

}

