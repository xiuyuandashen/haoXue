package com.zlf.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlf.edu.entity.Subject;
import com.zlf.edu.entity.excel.SubjectData;
import com.zlf.edu.service.SubjectService;
import com.zlf.servicebase.exceptionHandler.GlobalException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/03/25/17:19
 * @Description: easyExcel 读 监听器
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 由于 SubjectExcelListener 不能交给Spring管理 所以我们只能手动传入 subjectService



    public SubjectService subjectService;

    public SubjectExcelListener(){

    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // 读取excel数据，一行一行读取的数据
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw  new GlobalException(20001,"文件数据为空");
        }
        // 一行一行读取，每次读取有两个值，第一个是一级分类，第二个是二级分类

        Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(existOneSubject == null){//表示没有相同的一级分类
            existOneSubject  = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName()); //设置一级分类名称
            subjectService.save(existOneSubject);
        }

        String parentId = existOneSubject.getId();

        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), parentId);
        if(existTwoSubject == null){//表示没有相同的二级分类
            existTwoSubject  = new Subject();
            existTwoSubject.setParentId(parentId);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName()); //设置二级分类名称
            subjectService.save(existTwoSubject);
        }


    }

    // 判断一级分类不能重复添加
    private Subject existOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        Subject oneSubject = subjectService.getOne(queryWrapper);
        return oneSubject;
    }

    // 判断二级分类不能重复添加
    private Subject existTwoSubject(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        Subject twoSubject = subjectService.getOne(queryWrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
