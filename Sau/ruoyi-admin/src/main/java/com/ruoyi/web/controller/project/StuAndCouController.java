package com.ruoyi.web.controller.project;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.project.domain.Course;
import com.ruoyi.project.domain.Student;
import com.ruoyi.project.service.SacService;
import com.ruoyi.project.service.StudentService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxj
 */
@Controller
@Api("学生选课管理")
@RequestMapping("/project/stuandcou")
public class StuAndCouController extends BaseController {

    private String prefix = "project/stuandcou";

    @Autowired
    private SacService sacService;

    @GetMapping("/selected")
    @RequiresPermissions("system:selected:view")
    public String selected()
    {
        return prefix + "/selected";
    }

    @GetMapping("/optional")
    @RequiresPermissions("system:optional:view")
    public String optional(Model model)
    {
        long userId = ShiroUtils.getUserId();
        int score1 = sacService.getTotalPoint(userId);
        int score2 = 15 - score1;
        model.addAttribute("score1",score1);
        model.addAttribute("score2",score2);
        return prefix + "/optional";
    }

    @PostMapping("/stuAddCourse")
    @RequiresPermissions("system:optional:add")
    @ApiOperation(value = "添加选课信息")
    @Transactional
    @ResponseBody
    public AjaxResult stuAddCourse(@RequestParam(value = "ids") String courseId){
        long userId = ShiroUtils.getUserId();
        return sacService.stuAddCourse(courseId,userId);
    }

    @PostMapping("/selectCourseById")
    @RequiresPermissions("system:selected:list")
    @ApiOperation(value = "根据学生Id查找已选课程")
    @Transactional
    @ResponseBody
    public TableDataInfo selectCourseById(@RequestParam(value = "courseId",defaultValue = "0")long courseId,
                                          @RequestParam(value = "courseName",defaultValue = "") String courseName,
                                          @RequestParam(value = "teacherName",defaultValue = "") String teacherName){
        startPage();
        long userId = ShiroUtils.getUserId();
        List<Course> list = sacService.selectCourseById(userId,courseId,courseName,teacherName);
        return getDataTable(list);
    }


    @PostMapping("/deleteByCourseId")
    @RequiresPermissions("system:selected:remove")
    @ApiOperation(value = "根据课程Id删除已选课程")
    @Transactional
    @ResponseBody
    public AjaxResult deleteByCourseId(@RequestParam(value = "ids") String courseId){
        long userId = ShiroUtils.getUserId();
        int Count = sacService.deleteByCourseId(courseId,userId);
        if(Count > 0){
            return AjaxResult.success();
        }
        return AjaxResult.warn("删除课程失败");
    }
}
