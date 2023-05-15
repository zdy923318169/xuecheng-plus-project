package com.xuecheng.content.api;

import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseauditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 课程状态编辑接口
 * @version 1.0
 */
@Api(value = "课程状态编辑接口",tags = "课程状态编辑接口")
@RestController
public class CourseauditController {
    @Autowired
    private CourseauditService courseauditService;
    @ResponseBody
    @PostMapping ("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId){
        Long companyId = 1232141425L;
        courseauditService.commitAudit(companyId,courseId);

    }

}
