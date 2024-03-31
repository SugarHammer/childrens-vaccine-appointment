package com.ruoyi.project.mapper;

import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.StuAndCou;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Maxj
 */
@Mapper
public interface SacMapper {

    public void stuAddCourse(@Param("courseId")Long courseId, @Param("userId")long userId);

    public ArrayList<Long> getCourseByStuId(long userId);

    public int getTotalPoint(long userId);

    public List<Course> selectCourseById(@Param("userId") long userId,
                                         @Param("courseId") long courseId,
                                         @Param("courseName") String courseName,
                                         @Param("teacherName") String teacherName);

    public int deleteByCourseId(@Param("course")long course,@Param("userId") long userId);

    public List<Map<String,Object>> selectCourseByTeaId(@Param("teaId") long teaId,
                                                        @Param("courseId") long courseId,
                                                        @Param("courseName") String courseName,
                                                        @Param("studentId") long studentId,
                                                        @Param("studentName") String studentName);

    public List<Map<String,Object>> selectCourseByStuId(@Param("stuId") long stuId,
                                                        @Param("courseId") long courseId,
                                                        @Param("courseName") String courseName,
                                                        @Param("teacherName") String teacherName);

    public List<Map<String,Object>> selectAllCourse(@Param("courseId") long courseId,
                                                    @Param("courseName") String courseName,
                                                    @Param("teacherName") String teacherName,
                                                    @Param("studentId") long studentId,
                                                    @Param("departmentId") long departmentId);

    public StuAndCou getByCurrId(long curriculaId);

    public int addGrade(@Param("curriculaId") long curriculaId,
                        @Param("pacificScore")String pacificScore,
                        @Param("examScore")String examScore,
                        @Param("score") String score);
}
