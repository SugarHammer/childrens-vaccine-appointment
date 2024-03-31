package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.Student;
import com.ruoyi.project.domain.Teacher;
import com.ruoyi.project.mapper.StudentMapper;
import com.ruoyi.project.mapper.TeacherMapper;
import com.ruoyi.project.service.StudentService;
import com.ruoyi.project.service.TeacherService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    /**
     * 删除教师信息
     * @param teacherId
     * @return
     */
    @Override
    public int deleteTeacher(String teacherId) {
        Long[] userIds = Convert.toLongArray(teacherId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        sysUserMapper.deleteUserByIds(userIds);
        return teacherMapper.deleteTeacher(userIds);
    }

    /**
     * 删除与该教师相关的课程信息
     * @param teacherId
     */
    @Override
    public void deleteTeacher2(String teacherId) {
        Long[] userIds = Convert.toLongArray(teacherId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        teacherMapper.deleteTeacher2(userIds);
    }

    /**
     * 删除与该教师相关的课程信息
     * @param teacherId
     */
    @Override
    public void deleteTeacher3(String teacherId) {
        Long[] userIds = Convert.toLongArray(teacherId);
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        teacherMapper.deleteTeacher3(userIds);
    }

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 查询教师信息
     * @param teacherId
     * @param teacherName
     * @param departmentId
     * @return
     */
    @Override
    public List<Map<String,Object>> selectTeacher(long teacherId, String teacherName, long departmentId) {
        return teacherMapper.selectTeacher(teacherId,teacherName,departmentId);
    }

    @Override
    public Teacher selectTeacherById(long teacherId) {
        return teacherMapper.selectTeacherById(teacherId);
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
