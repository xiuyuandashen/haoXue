package com.zlf.edu.service;

import com.zlf.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zlf
 * @since 2021-03-25
 */
public interface SubjectService extends IService<Subject> {

    /**
    * @Description: 添加课程分类
    * @Param: [file] 上传Excel文件
    * @return: void
    * @Author: zlf
    * @Date: 2021/3/25
    */
    void saveSubject(MultipartFile file,SubjectService subjectService);
}
