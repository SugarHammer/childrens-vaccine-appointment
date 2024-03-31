package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Mapper
public interface CourseMapper {

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
    public int deleteCourse(Long[] courseId);

    /**
     * 删除与课程相关的学生信息
     * @param courseId
     * @return
     */
    public int deleteCourse2(Long[] courseId);

    /**
     * 更新课程信息
     * @param course
     * @return
     */
    public int updateCourse(Course course);

    /**
     * 查询课程信息
     * @return
     */
    public List<Map<String,Object>> selectCourse(@Param(value = "courseId") long courseId, @Param(value = "courseName") String courseName, @Param(value = "teacherName") String teacherName);

    /**
     * 根据Id查找课程信息
     * @param courseId
     * @return
     */
    public Course selectCourseById(@Param(value = "courseId") long courseId);

}
