package com.zlf.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zlf.edu.entity.Subject;
import com.zlf.edu.entity.excel.SubjectData;
import com.zlf.edu.listener.SubjectExcelListener;
import com.zlf.edu.mapper.SubjectMapper;
import com.zlf.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zlf
 * @since 2021-03-25
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file ,SubjectService subjectService) {
        try{
            // 文件输入流
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
