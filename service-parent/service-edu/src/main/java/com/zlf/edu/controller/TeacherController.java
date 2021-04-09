package com.zlf.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zlf.commonutils.vo.ResultVo;
import com.zlf.edu.entity.Teacher;
import com.zlf.edu.entity.vo.TeacherQueryVo;
import com.zlf.edu.fegin.OssFileService;
import com.zlf.edu.service.TeacherService;
import com.zlf.servicebase.exceptionHandler.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zlf
 * @since 2021-03-11
 */
@Slf4j
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    /**
    * @Description: 讲师列表
    * @Param: []
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @GetMapping("findAll")
    @ApiOperation("讲师列表")
    public ResultVo findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        //if(1==1) throw new GlobalException(200001,"出现自定义异常");
        return ResultVo.ok().data("items",list);
    }
    
    /**
    * @Description: 逻辑删除讲师
    * @Param: [id]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除讲师")
    public ResultVo removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable("id") String id){
        // 删除讲师头像
        teacherService.removeAvatarById(id);
        // 逻辑删除讲师
        boolean flag = teacherService.removeById(id);
        if(!flag) return   ResultVo.error().data("msg","逻辑删除失败");
        return ResultVo.ok();
    }
    /**
    * @Description: 逻辑删除讲师 多个
    * @Param: [idList]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @DeleteMapping("batch-remove")
    @ApiOperation("逻辑删除讲师列表")
    public ResultVo removeTeachers(@ApiParam(name = "id",value = "讲师id列表",required = true) @RequestBody List<String> idList){
        //if(idList==null || idList.size() < 1) return ResultVo.error().message("数据不存在!"); 前端判断
        boolean flag = teacherService.removeByIds(idList);
        if(!flag) return   ResultVo.error().data("msg","逻辑删除失败");
        return ResultVo.ok();
    }

    /**
    * @Description: 分页讲师列表
    * @Param: [current, limit]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation("分页讲师列表")
    public ResultVo pageListTeacher(@ApiParam(name = "current",value = "当前页",required = true,defaultValue = "1") @PathVariable("current") long current,
                             @ApiParam(name = "limit",value = "记录数" ,required = true,defaultValue = "5") @PathVariable("limit") long limit ){
        // current 当前页 size 记录数
        // 创建Page对象(mybaits-plus的)
        Page<Teacher> teacherPage = new Page<>(current,limit);
        // 分页查询
        teacherService.page(teacherPage,null);

        long total = teacherPage.getTotal(); // 总记录数
        List<Teacher> records = teacherPage.getRecords(); // 数据集合
        return ResultVo.ok().data("items",records).data("total",total);
    }


    /**
    * @Description: 分页条件查询
    * @Param: [current, limit, teacherQueryVo]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public ResultVo pageTeacherCondition(@ApiParam(name = "current",value = "当前页",required = true) @PathVariable("current") long current,
                                  @ApiParam(name = "limit",value = "记录数" ,required = true) @PathVariable("limit") long limit,
                                  @ApiParam(name = "teacherQueryVo",value = "查询条件",required = true)
                                      @RequestBody(required = false) TeacherQueryVo teacherQueryVo){ //required = false表示参数值可以为空
        Page<Teacher> page = new Page<>(current,limit);
        //System.out.println(teacherQueryVo);
        teacherService.pageQuery(page,teacherQueryVo);

        return ResultVo.ok().data("items",page.getRecords()).data("total",page.getTotal());
    }

    /**
    * @Description: 添加讲师
    * @Param: [teacher]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @PostMapping("addTeacher")
    @ApiOperation("添加讲师")
    public ResultVo addTeacher(@ApiParam(name = "teacher",value = "添加的讲师信息") @RequestBody Teacher teacher){
        //System.out.println(teacher);
        boolean save = teacherService.save(teacher);
        if(save){
            return ResultVo.ok().data("msg","添加讲师成功!");
        }else {
            return ResultVo.error().data("msg","添加讲师失败!");
        }
    }

    /**
    * @Description: 根据id查询讲师
    * @Param: [id]
    * @return: com.zlf.commonutils.vo.ResultVo
    * @Author: zlf
    * @Date: 2021/3/27
    */
    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public ResultVo getTeacher(@PathVariable("id") @ApiParam(name = "id",value = "讲师id",required = true) String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher==null){
            return ResultVo.error().data("msg","查询失败,无此用户");
        }
        return ResultVo.ok().data("teacher",teacher);
    }



   /**
   * @Description: 修改讲师信息,根据id
   * @Param: [teacher]
   * @return: com.zlf.commonutils.vo.ResultVo
   * @Author: zlf
   * @Date: 2021/3/27
   */
    @ApiOperation("修改讲师信息（根据id）")
    @PostMapping("updateTeacher")
    public ResultVo updateTeacher(@ApiParam(name = "teacher",value = "修改的讲师信息")@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if(b){
            return ResultVo.ok().data("msg","修改讲师信息成功!");
        }else {
            return ResultVo.error().data("msg","修改讲师信息失败!");
        }
    }

    @ApiOperation("根据关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public ResultVo selectNameListByKey(
       @ApiParam(name="key",value = "关键字")  @PathVariable("key") String key
    ){

        List<Map<String,Object>> nameList = teacherService.selectNameList(key);
        return ResultVo.ok().data("nameList",nameList);
    }

    @ApiOperation("远程调用测试")
    @GetMapping("test")
    public ResultVo test(){
        log.info("test");
        return ossFileService.test();
    }

    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public ResultVo testConcurrent(){
        log.info("test_concurrent");
        //ossFileService.test();
        return ResultVo.ok();
    }



    @GetMapping("message1")
    public String message1(){
        return "message1";
    }

    @GetMapping("message2")
    public String message2(){
        return "message2";
    }
}

