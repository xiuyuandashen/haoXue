package com.zlf.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlf.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlf.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zlf
 * @since 2021-03-11
 */
public interface TeacherService extends IService<Teacher> {

    void pageQuery(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo);

    /**
    * @Description: 模糊查询讲师名字列表
    * @Param: [key] 模糊名字
    * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: zlf
    * @Date: 2021/4/2
    */
    List<Map<String, Object>> selectNameList(String key);

    /**
    * @Description: 根据id深处讲师头像
    * @Param: [id]
    * @return: void
    * @Author: zlf
    * @Date: 2021/4/2
    */
    boolean removeAvatarById(String id);
}
