package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description 师资管理接口
 * @version 1.0
 */
public interface CourseTeacherService {
    /**
     * 师资查询接口
     * @param courseId 课程id
     * @return
     */
    public List<CourseTeacher> list(Long courseId);

    /**
     * 新增师资接口
     * @param companyId  机构id
     * @param courseTeacher
     * @return
     */
    public CourseTeacher insert(Long companyId, CourseTeacher courseTeacher);

    /**
     * 删除师资接口
     * @param courseId 课程id
     * @param id 主键id
     */
    void dele(Long companyId,Long courseId,Long id);
}
