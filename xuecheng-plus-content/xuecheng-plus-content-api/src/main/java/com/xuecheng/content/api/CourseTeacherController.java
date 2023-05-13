package com.xuecheng.content.api;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 课程计划编辑接口
 * @version 1.0
 */
@Api(value = "师资管理编辑接口",tags = "师资管理编辑接口")
@RestController
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;
    @ApiOperation("师资查询")
    @GetMapping("courseTeacher/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable Long courseId){
        return courseTeacherService.list(courseId);
    }
    @ApiOperation("添加/修改师资")
    @PostMapping("courseTeacher")
    public CourseTeacher insert(@RequestBody CourseTeacher courseTeacher){
        Long id =1232141425L;
        return courseTeacherService.insert(id,courseTeacher);
    }
    @ApiOperation("删除师资")
    @DeleteMapping("courseTeacher/course/{courseId}/{id}")
    public void dele(@PathVariable("courseId") Long courseId,@PathVariable("id") Long id){
        Long cid =1232141425L;
        courseTeacherService.dele(cid,courseId,id);
    }


}
