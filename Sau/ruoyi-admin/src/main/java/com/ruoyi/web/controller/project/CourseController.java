package com.ruoyi.web.controller.project;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.Student;
import com.ruoyi.project.domain.Teacher;
import com.ruoyi.project.service.CourseService;
import com.ruoyi.project.service.StudentService;
import com.ruoyi.project.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Controller
@Api("课程信息管理")
@RequestMapping("/project/course")
public class CourseController extends BaseController {

    private String prefix = "project/course";

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;


    @GetMapping()
    @RequiresPermissions("system:course:view")
    public String role()
    {
        return prefix + "/course";
    }

    /**
     * 新增教师
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 添加课程信息
     * @param course
     * @return
     */
    @PostMapping("/addCourse")
    @RequiresPermissions("system:course:add")
    @ApiOperation(value = "添加课程信息")
    @ResponseBody
    public AjaxResult addCourse(Course course){
        Teacher teacher = teacherService.selectTeacherById(course.getTeacherId());
        if(teacher == null){
            return AjaxResult.warn("教师信息不存在");
        }
        course.setTeacherName(teacher.getTeacherName());
        if(!course.getPacific().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的平时成绩占比不是数字");
        }
        if(!course.getExam().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的考试成绩占比不是数字");
        }
        double pacific = Double.parseDouble(course.getPacific());
        double exam = Double.parseDouble(course.getExam());
        if(pacific< 0 || pacific > 100){
            return AjaxResult.warn("请输入（0~100）数字");
        }
        if(exam < 0 || exam > 100){
            return AjaxResult.warn("请输入（0~100）数字");
        }
        if(pacific + exam != 100){
            return AjaxResult.warn("平时成绩占比+考试成绩占比不等于100%");
        }
        int Count = courseService.addCourse(course);
        if(Count > 0){
            return AjaxResult.success("添加课程信息成功");
        }
        return AjaxResult.warn("添加课程信息失败");
    }

    @PostMapping("/deleteCourse")
    @RequiresPermissions("system:course:remove")
    @ApiOperation(value = "删除课程信息")
    @Transactional
    @ResponseBody
    public AjaxResult deleteCourse(@RequestParam(value = "ids") String courseId){
        int Count = courseService.deleteCourse(courseId);
        courseService.deleteCourse2(courseId);
        if(Count > 0){
            return AjaxResult.success("删除课程信息成功");
        }
        return AjaxResult.warn("删除课程信息失败");
    }


    /**
     * 修改课程信息
     */
    @GetMapping("/edit/{courseId}")
    public String edit(@PathVariable("courseId") Long courseId, ModelMap mmap)
    {
        mmap.put("course", courseService.selectCourseById(courseId));
        return prefix + "/edit";
    }



    @PostMapping("/updateCourse")
    @RequiresPermissions("system:course:edit")
    @ApiOperation(value = "修改课程信息")
    @ResponseBody
    public AjaxResult updateCourse(Course course){
        if(!course.getPacific().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的平时成绩占比不是数字");
        }
        if(!course.getExam().matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的考试成绩占比不是数字");
        }
        double pacific = Double.parseDouble(course.getPacific());
        double exam = Double.parseDouble(course.getExam());
        if(pacific< 0 || pacific > 100){
            return AjaxResult.warn("请输入的平时成绩占比在（0~100）之间");
        }
        if(exam < 0 || exam > 100){
            return AjaxResult.warn("请输入的考试成绩占比在（0~100）之间");
        }
        if(pacific + exam != 100){
            return AjaxResult.warn("平时成绩+考试成绩占比不等于100%");
        }
        int Count = courseService.updateCourse(course);
        if(Count > 0){
            return AjaxResult.success("修改课程信息成功");
        }
        return AjaxResult.warn("修改课程信息失败");
    }

    @PostMapping("/selectCourse")
    @RequiresPermissions("system:course:list")
    @ApiOperation(value = "查找课程信息")
    @ResponseBody
    public TableDataInfo selectCourse(@RequestParam(value = "courseId",defaultValue = "0")long courseId,
                                       @RequestParam(value = "courseName",defaultValue = "") String courseName,
                                       @RequestParam(value = "teacherName",defaultValue = "") String teacherName){
        startPage();
        List<Map<String,Object>> list = courseService.selectCourse(courseId,courseName,teacherName);
        return getDataTable(list);
    }
}
