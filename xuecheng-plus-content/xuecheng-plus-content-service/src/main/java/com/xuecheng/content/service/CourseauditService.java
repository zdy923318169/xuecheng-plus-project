package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseBase;

/**
 * @description 课程状态编辑接口
 * @version 1.0
 */
public interface CourseauditService {
    /**
     * @description 提交审核
     * @param courseId  课程id
     * @return void
     * @author Mr.M
     * @date 2022/9/18 10:31
     */
    public void commitAudit(Long companyId,Long courseId);

}
