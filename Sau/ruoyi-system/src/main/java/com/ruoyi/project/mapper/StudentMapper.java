package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Mapper
public interface StudentMapper {

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    public int addStudent(Student student);

    /**
     * 删除学生信息
     * @param studentId
     * @return
     */
    public int deleteStudent(Long[] studentId);

    /**
     * 删除与学生相关的课程信息
     * @param studentId
     * @return
     */
    public int deleteStudent2(Long[] studentId);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    public int updateStudent(Student student);

    /**
     * 查询学生信息
     * @return
     */
    public List<Map<String,Object>> selectStudent(@Param(value = "studentId") long studentId,@Param(value = "studentName") String studentName,@Param(value = "departmentId") long departmentId);

    /**
     * 根据Id查找学生信息
     * @param studentId
     * @return
     */
    public Student selectStudentById(@Param(value = "studentId") long studentId);

    /**
     * 根据查找学生信息列表
     * @return
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 获取自增的用户id
     * @return
     */
    public long getId();
}
