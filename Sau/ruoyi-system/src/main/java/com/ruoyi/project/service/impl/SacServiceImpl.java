package com.ruoyi.project.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.StuAndCou;
import com.ruoyi.project.mapper.SacMapper;
import com.ruoyi.project.service.CourseService;
import com.ruoyi.project.service.SacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Maxj
 */
@Service
public class SacServiceImpl implements SacService {

    @Autowired
    private SacMapper sacMapper;

    @Autowired
    private SacService sacService;

    @Autowired
    private CourseService courseService;

    /**
     * 添加学生选课信息
     * @param courseId
     * @return
     */
    public AjaxResult stuAddCourse(String courseId, long userId){
        ArrayList<Long> selectCourses = sacService.getCourseByStuId(userId);
        int totalPoint = sacService.getTotalPoint(userId);
        Long[] courseIds = Convert.toLongArray(courseId);
        for(Long id : courseIds){
            Course course = courseService.selectCourseById(id);
            if(course.getCoursePoint()+totalPoint > 15){
                return AjaxResult.warn("本学期已选课程超过15学分");
            }
            Boolean flag = false;
            for(Long num : selectCourses){
                if(num.longValue() == id.longValue()){
                    flag = true;
                    break;
                }
            }
            if(flag){
                return AjaxResult.warn("请不要重复选课！");
            }
            sacMapper.stuAddCourse(id,userId);
        }
        return AjaxResult.success("选课成功");
    }

    /**
     * 根据学生id获得已选课程号
     * @param userId
     * @return
     */
    @Override
    public ArrayList<Long> getCourseByStuId(long userId) {
        return sacMapper.getCourseByStuId(userId);
    }

    /**
     * 根据学生ID获得已选课程总分
     * @param userId
     * @return
     */
    @Override
    public int getTotalPoint(long userId) {
        return sacMapper.getTotalPoint(userId);
    }

    /**
     * 根据学生Id查找已选课程
     * @return
     */
    public List<Course> selectCourseById(long userId,long courseId,String courseName,String teacherName){
        return sacMapper.selectCourseById(userId,courseId,courseName,teacherName);
    }

    /**
     * 根据学生Id查找已选课程成绩
     * @return
     */
    public List<Map<String,Object>> selectCourseByStuId(long stuId,long courseId,String courseName,String teacherName){
        return sacMapper.selectCourseByStuId(stuId,courseId,courseName,teacherName);
    }

    /**
     * 查询所有选课信息
     * @return
     */
    public List<Map<String,Object>> selectAllCourse(long courseId,String courseName,String teacherName,long studentId,long departmentId){
        return sacMapper.selectAllCourse(courseId,courseName,teacherName,studentId,departmentId);
    }

    /**
     * 根据教师Id查询选课信息
     * @return
     */
    public List<Map<String,Object>> selectCourseByTeaId(long teaId,long courseId,String courseName,long studentId,String studentName){
        return sacMapper.selectCourseByTeaId(teaId,courseId,courseName,studentId,studentName);
    }

    /**
     * 根据课程Id删除已选课程
     * @param courseId
     * @param userId
     * @return
     */
    public int deleteByCourseId(String courseId,long userId){
        long course = Integer.parseInt(courseId);
        return sacMapper.deleteByCourseId(course,userId);
    }

    /**
     * 根据选课编号查询选课信息
     * @return
     */
    public StuAndCou getByCurrId(long curriculaId){
        return sacMapper.getByCurrId(curriculaId);
    }

    /**
     * 根据选课编号添加成绩
     * @param curriculaId
     * @param score
     * @return
     */
    public int addGrade(long curriculaId,String pacificScore,String examScore,String score){
        return sacMapper.addGrade(curriculaId,pacificScore,examScore,score);
    }
}
