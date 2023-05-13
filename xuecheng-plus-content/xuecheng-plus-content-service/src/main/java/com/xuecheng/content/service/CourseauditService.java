package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseBase;

/**
 * @description 课程状态编辑接口
 * @version 1.0
 */
public interface CourseauditService {
    /**
     * 提交审核接口
     * @param id 课程id
     * @return
     */
    CourseBase commit(Long id);
}
