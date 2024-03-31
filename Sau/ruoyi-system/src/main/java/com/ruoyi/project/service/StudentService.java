package com.ruoyi.project.service;


import com.ruoyi.project.domain.Student;
import com.ruoyi.system.domain.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface StudentService {

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
    public int deleteStudent(String studentId);

    /**
     * 删除与该学生相关的课程信息
     * @param studentId
     */
    public void deleteStudent2(String studentId);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    public int updateStudent(Student student);

    /**
     * 查找学生信息
     * @return
     */
    public List<Map<String,Object>> selectStudent(long studentId,String studentName,long departmentId);


    /**
     * 根据Id查找学生信息
     * @param studentId
     * @return
     */
    public Student selectStudentById(long studentId);

    /**
     * 根据查找学生信息列表
     * @return
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importUser(List<Student> userList, Boolean isUpdateSupport, String operName);

}
