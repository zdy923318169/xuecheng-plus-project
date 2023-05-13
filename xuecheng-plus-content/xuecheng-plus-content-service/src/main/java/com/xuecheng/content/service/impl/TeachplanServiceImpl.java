package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.execption.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 课程计划service接口实现类
 * @author Mr.M
 * @date 2022/9/9 11:14
 * @version 1.0
 */
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;
    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {

        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if(id!=null){
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }else{
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count+1);
            BeanUtils.copyProperties(teachplanDto,teachplanNew);

            teachplanMapper.insert(teachplanNew);

        }

    }

    @Override
    @Transactional
    public void deleTeachplan(Long id) {
        if (teachplanMapper.selectById(id)==null){
            XueChengPlusException.cast("课程为空，删除失败");
        }
        LambdaQueryWrapper<Teachplan> teachplanLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teachplanLambdaQueryWrapper.eq(Teachplan::getParentid,id);
        List<Teachplan> teachplans = teachplanMapper.selectList(teachplanLambdaQueryWrapper);

        if (teachplans.size()==0){
            teachplanMapper.deleteById(id);
        }else {
            XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
        }

    }

    @Override
    public void movedownTeachplan(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        Long parentid = teachplan.getParentid();
        if (parentid==0){
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teachplan::getParentid,parentid);
            queryWrapper.eq(Teachplan::getCourseId,teachplan.getCourseId());
            queryWrapper.eq(Teachplan::getOrderby,teachplan.getOrderby()+1);
            Teachplan teachplan1 = teachplanMapper.selectOne(queryWrapper);
            teachplan.setOrderby(teachplan1.getOrderby());
            teachplan1.setOrderby(teachplan.getOrderby());
            teachplanMapper.updateById(teachplan1);
            teachplanMapper.updateById(teachplan);

        }else {
            LambdaQueryWrapper<Teachplan> teachplanLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teachplanLambdaQueryWrapper.eq(Teachplan::getParentid,parentid);
            List<Teachplan> teachplans = teachplanMapper.selectList(teachplanLambdaQueryWrapper);
            if (teachplans.size()==1){
                XueChengPlusException.cast("移动失败");
            }
            Integer orderby = teachplan.getOrderby();
            Integer orderby2=orderby+1;
            teachplanLambdaQueryWrapper.eq(Teachplan::getOrderby,orderby2);
            Teachplan teachplan1 = teachplanMapper.selectOne(teachplanLambdaQueryWrapper);
            if (teachplan1==null){
                XueChengPlusException.cast("移动失败");
            }
            teachplan1.setOrderby(orderby);
            teachplanMapper.updateById(teachplan1);
            teachplan.setOrderby(orderby2);
            teachplanMapper.updateById(teachplan);
        }

    }

    @Override
    public void moveupTeachplan(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        Long parentid = teachplan.getParentid();
        if (parentid==0){
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teachplan::getParentid,parentid);
            queryWrapper.eq(Teachplan::getCourseId,teachplan.getCourseId());
            queryWrapper.eq(Teachplan::getOrderby,teachplan.getOrderby()-1);
            Teachplan teachplan1 = teachplanMapper.selectOne(queryWrapper);
            teachplan.setOrderby(teachplan1.getOrderby());
            teachplan1.setOrderby(teachplan.getOrderby());
            teachplanMapper.updateById(teachplan1);
            teachplanMapper.updateById(teachplan);

        }else {
            LambdaQueryWrapper<Teachplan> teachplanLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teachplanLambdaQueryWrapper.eq(Teachplan::getParentid,parentid);
            List<Teachplan> teachplans = teachplanMapper.selectList(teachplanLambdaQueryWrapper);
            if (teachplans.size()==1){
                XueChengPlusException.cast("移动失败");
            }
            Integer orderby = teachplan.getOrderby();
            Integer orderby2=orderby-1;
            teachplanLambdaQueryWrapper.eq(Teachplan::getOrderby,orderby2);
            Teachplan teachplan1 = teachplanMapper.selectOne(teachplanLambdaQueryWrapper);
            if (teachplan1==null){
                XueChengPlusException.cast("移动失败");
            }
            teachplan1.setOrderby(orderby);
            teachplanMapper.updateById(teachplan1);
            teachplan.setOrderby(orderby2);
            teachplanMapper.updateById(teachplan);
        }
    }

    /**
     * @description 获取最新的排序号
     * @param courseId  课程id
     * @param parentId  父课程计划id
     * @return int 最新排序号
     * @author Mr.M
     * @date 2022/9/9 13:43
     */
    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count;
    }

}
