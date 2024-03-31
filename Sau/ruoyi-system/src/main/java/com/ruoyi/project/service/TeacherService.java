package com.ruoyi.project.service;


import com.ruoyi.project.domain.Student;
import com.ruoyi.project.domain.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface TeacherService {

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    public int addTeacher(Teacher teacher);

    /**
     * 删除教师信息
     * @param teacherId
     * @return
     */
    public int deleteTeacher(String teacherId);

    /**
     * 删除与该教师相关的课程信息
     * @param teacherId
     */
    public void deleteTeacher2(String teacherId);

    /**
     * 删除与该教师相关的学生选课信息信息
     * @param teacherId
     */
    public void deleteTeacher3(String teacherId);

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 查找教师信息
     * @return
     */
    public List<Map<String,Object>> selectTeacher(long teacherId, String teacherName, long departmentId);


    /**
     * 根据Id查找学生信息
     * @param teacherId
     * @return
     */
    public Teacher selectTeacherById(long teacherId);
}
