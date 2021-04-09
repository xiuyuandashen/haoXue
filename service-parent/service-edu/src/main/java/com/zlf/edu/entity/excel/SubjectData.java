package com.zlf.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/03/25/17:12
 * @Description:
 */
@Data
public class SubjectData {


    @ExcelProperty(index = 0) //第一列
    private String oneSubjectName;

    @ExcelProperty(index = 1) // 第二列
    private String twoSubjectName;
}
