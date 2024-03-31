package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.Student;
import com.ruoyi.project.mapper.CourseMapper;
import com.ruoyi.project.mapper.StudentMapper;
import com.ruoyi.project.service.CourseService;
import com.ruoyi.project.service.StudentService;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 添加学生信息
     * @param course
     * @return
     */
    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    /**
     * 删除学生信息
     * @param courseId
     * @return
     */
    @Override
    public int deleteCourse(String courseId) {
        Long[] userIds = Convert.toLongArray(courseId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        return courseMapper.deleteCourse(userIds);
    }

    /**
     * 删除与该学生相关的课程信息
     * @param courseId
     */
    @Override
    public void deleteCourse2(String courseId) {
        Long[] userIds = Convert.toLongArray(courseId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        courseMapper.deleteCourse2(userIds);
    }

    /**
     * 更新学生信息
     * @param course
     * @return
     */
    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    /**
     * 查询学生信息
     * @param courseId
     * @param courseName
     * @param teacherName
     * @return
     */
    @Override
    public List<Map<String,Object>> selectCourse(long courseId, String courseName, String teacherName) {
        return courseMapper.selectCourse(courseId,courseName,teacherName);
    }

    @Override
    public Course selectCourseById(long courseId) {
        return courseMapper.selectCourseById(courseId);
    }


    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            throw new BusinessException("不允许操作超级管理员用户");
        }
    }
}
