package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 课程基本信息管理业务接口
 */
public interface CourseBaseInfoService {
    /**
     *课程查询接口
     * @param pageParams  分页参数
     * @param queryCourseParamsDto   查询条件
     * @return
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * @description 添加课程基本信息
     * @param companyId  教学机构id
     * @param addCourseDto  课程基本信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * 根据id查询
     * @param courseId 主键id
     * @return
     */
    public CourseBaseInfoDto getCourseBaseInfo(long courseId);

    /**
     * 修改课程基础信息
     * @param editCourseDto
     * @return
     */

    public CourseBaseInfoDto updateCourseBase(Long companyId,@RequestBody @Validated EditCourseDto editCourseDto);

    /**
     * 删除课程
     * @param id 课程id
     */
    public void dele( Long id);
}
