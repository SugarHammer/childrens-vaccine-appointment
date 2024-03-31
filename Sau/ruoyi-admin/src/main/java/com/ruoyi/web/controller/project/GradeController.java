package com.ruoyi.web.controller.project;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.service.DeptService;
import com.ruoyi.project.domain.Course;
import com.ruoyi.project.service.CourseService;
import com.ruoyi.project.service.SacService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Controller
@Api("成绩管理")
@RequestMapping("/project/grade")
public class GradeController extends BaseController{

    private String prefix = "project/grade";

    @Autowired
    private SacService sacService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService userService;

    @GetMapping("/log")
    @RequiresPermissions("system:log:view")
    public String log()
    {
        return prefix + "/logGrade";
    }

    @GetMapping("/query")
    @RequiresPermissions("system:query:view")
    public String query()
    {
        return prefix + "/queryGrade";
    }

    @GetMapping("/all")
    @RequiresPermissions("system:all:view")
    public String all()
    {
        return prefix + "/allGrade";
    }

    @GetMapping("/edit/{curriculaId}")
    public String edit(@PathVariable("curriculaId")Long curriculaId, ModelMap mmap)
    {
        mmap.put("stuandcou", sacService.getByCurrId(curriculaId));
        return prefix + "/edit";
    }


    @PostMapping("/addGrade")
    @RequiresPermissions("system:log:edit")
    @ApiOperation(value = "根据选课编号添加成绩")
    @Transactional
    @ResponseBody
    public AjaxResult addGrade(@RequestParam("curriculaId")long curriculaId,
                               @RequestParam("pacificScore")String pacificScore,
                               @RequestParam("examScore")String examScore){
        if(!pacificScore.matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的平时成绩不是数字");
        }
        if(!examScore.matches("-?[0-9]+.?[0-9]*")){
            return AjaxResult.warn("输入的考试成绩不是数字");
        }
        if(Double.parseDouble(pacificScore) < 0 || Double.parseDouble(pacificScore) > 100){
            return AjaxResult.warn("请输入的平时成绩在（0~100）之间");
        }
        if(Double.parseDouble(examScore) < 0 || Double.parseDouble(examScore) > 100){
            return AjaxResult.warn("请输入的考试成绩在（0~100）之间");
        }
        Course course = courseService.selectCourseById(sacService.getByCurrId(curriculaId).getCourseId());
        double pacific = Double.parseDouble(course.getPacific());
        double exam = Double.parseDouble(course.getExam());
        String score = String.valueOf(new DecimalFormat("#.00").format((pacific*Double.parseDouble(pacificScore) + exam*Double.parseDouble(examScore))/100.0));
        int Count = sacService.addGrade(curriculaId,pacificScore,examScore,score);
        if(Count > 0){
            return AjaxResult.success("成绩添加成功");
        }
        return AjaxResult.warn("成绩添加失败");
    }


    @PostMapping("/selectCourseByTeaId")
    @RequiresPermissions("system:log:list")
    @ApiOperation(value = "根据教师Id查询选课信息")
    @Transactional
    @ResponseBody
    public TableDataInfo selectCourseByTeaId(@RequestParam(value = "courseId",defaultValue = "0")long courseId,
                                             @RequestParam(value = "courseName",defaultValue = "") String courseName,
                                             @RequestParam(value = "studentId",defaultValue = "0")long studentId,
                                             @RequestParam(value = "studentName",defaultValue = "") String studentName){
        startPage();
        long userId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = sacService.selectCourseByTeaId(userId,courseId,courseName,studentId,studentName);
        return getDataTable(list);
    }

    @PostMapping("/selectCourseByStuId")
    @RequiresPermissions("system:query:list")
    @ApiOperation(value = "根据学生Id查找已选课程成绩")
    @Transactional
    @ResponseBody
    public TableDataInfo selectCourseByStuId(@RequestParam(value = "courseId",defaultValue = "0")long courseId,
                                             @RequestParam(value = "courseName",defaultValue = "") String courseName,
                                             @RequestParam(value = "teacherName",defaultValue = "") String teacherName){
        startPage();
        long userId = ShiroUtils.getUserId();
        List<Map<String,Object>> list = sacService.selectCourseByStuId(userId,courseId,courseName,teacherName);
        for(Map<String,Object> map : list) {
            if (map.containsKey("departmentId")) {
                map.put("departmentId", deptService.getDeptName(Long.parseLong(map.get("departmentId").toString())));
            }
        }
        return getDataTable(list);
    }

    @PostMapping("/selectAllCourse")
    @RequiresPermissions("system:all:list")
    @ApiOperation(value = "查询全部选课情况")
    @Transactional
    @ResponseBody
    public TableDataInfo selectCourseByStuId(@RequestParam(value = "courseId",defaultValue = "0")long courseId,
                                             @RequestParam(value = "courseName",defaultValue = "") String courseName,
                                             @RequestParam(value = "teacherName",defaultValue = "") String teacherName,
                                             @RequestParam(value = "studentId",defaultValue = "0")long studentId,
                                             @RequestParam(value = "departmentId",defaultValue = "0") long departmentId){
        startPage();
        List<Map<String,Object>> list = sacService.selectAllCourse(courseId,courseName,teacherName,studentId,departmentId);
        for(Map<String,Object> map : list){
            if(map.containsKey("departmentId")){
                map.put("departmentId",deptService.getDeptName(Long.parseLong(map.get("departmentId").toString())));
            }
        }
        return getDataTable(list);
    }
}
