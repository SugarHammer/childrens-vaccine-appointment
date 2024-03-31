package com.ruoyi.project.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.StuAndCou;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Maxj
 */
public interface SacService {

    /**
     * 添加学生选课信息
     * @param courseId
     * @return
     */
    public AjaxResult stuAddCourse(String courseId, long userId);

    /**
     * 根据用户id获取课程号
     * @param userId
     * @return
     */
    public ArrayList<Long> getCourseByStuId (long userId);

    /**
     * 根据用户获取总选课学分
     * @param userId
     * @return
     */
    public int getTotalPoint(long userId);

    /**
     * 根据学生Id查找已选课程
     * @return
     */
    public List<Course> selectCourseById(long userId,long courseId,String courseName,String teacherName);

    /**
     * 根据学生Id查找已选课程成绩
     * @return
     */
    public List<Map<String,Object>>  selectCourseByStuId(long stuId,long courseId,String courseName,String teacherName);

    /**
     * 查询所有选课信息
     * @return
     */
    public List<Map<String,Object>> selectAllCourse(long courseId,String courseName,String teacherName,long studentId,long departmentId);

    /**
     * 根据教师Id查询选课信息
     * @return
     */
    public List<Map<String,Object>> selectCourseByTeaId(long teaId,long courseId,String courseName,long studentId,String studentName);


    /**
     * 根据课程Id删除已选课程
     * @param courseId
     * @param userId
     * @return
     */
    public int deleteByCourseId(String courseId,long userId);

    /**
     * 根据选课编号查询选课信息
     * @return
     */
    public StuAndCou getByCurrId(long curriculaId);

    /**
     * 根据选课编号添加成绩
     * @param curriculaId
     * @param score
     * @return
     */
    public int addGrade(long curriculaId,String pacificScore,String examScore,String score);
}
