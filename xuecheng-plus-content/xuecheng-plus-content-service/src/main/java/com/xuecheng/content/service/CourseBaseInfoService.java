package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

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
}