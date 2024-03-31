package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.Student;
import com.ruoyi.project.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Mapper
public interface TeacherMapper {

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
    public int deleteTeacher(Long[] teacherId);

    /**
     * 删除与教师相关的课程信息
     * @param teacherId
     * @return
     */
    public int deleteTeacher2(Long[] teacherId);

    /**
     * 删除与教师相关的学生选课信息
     * @param teacherId
     */
    public void deleteTeacher3(Long[] teacherId);

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 查询教师信息
     * @return
     */
    public List<Map<String,Object>> selectTeacher(@Param(value = "teacherId") long teacherId, @Param(value = "teacherName") String teacherName, @Param(value = "departmentId") long departmentId);

    /**
     * 根据Id查找教师信息
     * @param teacherId
     * @return
     */
    public Teacher selectTeacherById(@Param(value = "teacherId") long teacherId);
}
