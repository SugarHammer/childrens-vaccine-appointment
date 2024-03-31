package com.ruoyi.project.service;


import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.Student;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
public interface CourseService {

    /**
     * 添加课程信息
     * @param course
     * @return
     */
    public int addCourse(Course course);

    /**
     * 删除课程信息
     * @param courseId
     * @return
     */
    public int deleteCourse(String courseId);

    /**
     * 删除与该课程相关的学生信息
     * @param courseId
     */
    public void deleteCourse2(String courseId);

    /**
     * 更新课程信息
     * @param course
     * @return
     */
    public int updateCourse(Course course);

    /**
     * 查找课程信息
     * @return
     */
    public List<Map<String,Object>> selectCourse(long courseId, String courseName, String teacherName);


    /**
     * 根据Id查找课程信息
     * @param courseId
     * @return
     */
    public Course selectCourseById(long courseId);

}
