package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.execption.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @description 师资管理service接口实现类
 * @version 1.0
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;
    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Override
    public List<CourseTeacher> list(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> courseTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseTeacherLambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(courseTeacherLambdaQueryWrapper);
        return courseTeachers;
    }

    @Override
    public CourseTeacher insert(Long companyId, CourseTeacher courseTeacher) {
        CourseBase courseBase = courseBaseMapper.selectById(courseTeacher.getCourseId());
        if (!(companyId.equals(courseBase.getCompanyId()))){
            XueChengPlusException.cast("只允许向机构自己的课程中添加老师");
        }
        if (courseTeacher.getId()==null){
            int insert = courseTeacherMapper.insert(courseTeacher);
        }else {
            courseTeacherMapper.updateById(courseTeacher);
        }

        CourseTeacher courseTeacher1 = courseTeacherMapper.selectById(courseTeacher.getId());
        return courseTeacher1 ;
    }

    @Override
    public void dele(Long companyId,Long courseId, Long id) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (!(companyId.equals(courseBase.getCompanyId()))){
            XueChengPlusException.cast("只允许向机构自己的课程中添加老师");
        }
        courseTeacherMapper.deleteById(id);
    }
}
