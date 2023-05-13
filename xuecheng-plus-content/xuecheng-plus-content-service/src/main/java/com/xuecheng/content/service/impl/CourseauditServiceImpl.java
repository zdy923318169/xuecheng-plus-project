package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseauditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseauditServiceImpl implements CourseauditService {
    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Override
    public CourseBase commit(Long id) {
        CourseBase courseBase = courseBaseMapper.selectById(id);
        courseBase.setAuditStatus("202003");
        courseBaseMapper.updateById(courseBase);
        CourseBase courseBase1 = courseBaseMapper.selectById(courseBase.getId());
        return courseBase1;
    }
}
